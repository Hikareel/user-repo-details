package org.example.userrepodetails.service

import com.google.gson.Gson
import org.example.userrepodetails.config.ApiConfiguration
import org.springframework.http.*
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.WebClientResponseException
import kotlinx.coroutines.*
import org.example.userrepodetails.entity.*
import org.example.userrepodetails.entity.git_api_json.branches.BranchJson
import org.example.userrepodetails.entity.git_api_json.repos.RepoJson

@Service
class GitHubService(
	private val apiConfiguration: ApiConfiguration,
	private val webClient: WebClient,
	private val gson: Gson
) {
	fun getUserData(username: String): String? = runBlocking {
		val res = checkUserExists(username)
		if (res != null) {
			return@runBlocking gson.toJson(res)
		}
		return@runBlocking gson.toJson(
			UserData(
				username,
				getReposData(username)
			)
		)
	}

	suspend fun getReposData(
		username: String
	): List<Repository> = coroutineScope {
		val repoList: MutableList<Repository> = mutableListOf()
		webClient.get()
			.uri("${apiConfiguration.url}/users/$username/repos")
			.headers { getUserAgentHeader() }
			.retrieve()
			.bodyToFlux(RepoJson::class.java)
			.collectList()
			.block()?.forEach {
				launch {
					if (!it.fork) {
						repoList.add(
							Repository(
								it.name,
								getRepoBranchesData(username, it.name)
							)
						)
					}
				}
			}
		return@coroutineScope repoList
	}

	suspend fun getRepoBranchesData(
		username: String, repoName: String
	): List<Branch> = coroutineScope {
		val branchList: MutableList<Branch> = mutableListOf()
		webClient.get()
			.uri("${apiConfiguration.url}/repos/$username/$repoName/branches")
			.headers { getUserAgentHeader() }
			.retrieve()
			.bodyToFlux(BranchJson::class.java)
			.collectList()
			.block()?.forEach {
				launch {
					branchList.add(
						Branch(
							it.name,
							it.commit.sha
						)
					)
				}
			}
		return@coroutineScope branchList
	}

	fun checkUserExists(username: String): Message? {
		return try {
			webClient.get()
				.uri("${apiConfiguration.url}/users/$username")
				.headers { getUserAgentHeader() }
				.retrieve()
				.toBodilessEntity()
				.block()
			null
		} catch (ex: WebClientResponseException.NotFound) {
			Message("404 Code", "User not found")
		}
	}

	fun getUserAgentHeader(): HttpHeaders {
		val headers = HttpHeaders()
		headers.add("User-Agent", apiConfiguration.agent)
		headers.add("Accept", apiConfiguration.accept)
		headers.add("Authorization", apiConfiguration.token)
		return headers
	}
}