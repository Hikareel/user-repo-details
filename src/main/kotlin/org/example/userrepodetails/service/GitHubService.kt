package org.example.userrepodetails.service

import com.google.gson.Gson
import org.example.userrepodetails.config.ApiConfiguration
import org.example.userrepodetails.entity.Branch
import org.example.userrepodetails.entity.Message
import org.example.userrepodetails.entity.Repository
import org.example.userrepodetails.entity.UserData
import org.springframework.http.*
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.WebClientResponseException
import java.util.LinkedHashMap
import kotlinx.coroutines.*

@Service
class GitHubService(
    val apiConfiguration: ApiConfiguration,
    val gson: Gson
) {
    fun getUserData(username: String): String?  = runBlocking{
        val res = checkUserExists(username)
        if(res != null){
            return@runBlocking gson.toJson(res)
        }
        return@runBlocking gson.toJson(UserData(
            username,
            getReposData(username)
        ))
    }

    suspend fun getReposData(
        username: String
    ): List<Repository> = coroutineScope{
        val repoList: MutableList<Repository> = mutableListOf()
        val response = WebClient.create().get()
            .uri("${apiConfiguration.url}/users/$username/repos")
            .headers { getUserAgentHeader() }
            .retrieve()
            .bodyToMono(List::class.java)
            .block()
        response?.forEach{
            launch{
                val responseMap = (it as LinkedHashMap<String, String>)
                if (responseMap["fork"].toString() == "false"){
                    repoList.add(
                        Repository(
                            responseMap["name"]!!,
                            responseMap["name"]?.let { it1 ->
                                getRepoBranchesData(username, it1)
                            }!!
                        )
                    )
                }
            }
        }
        return@coroutineScope repoList
    }

    suspend fun getRepoBranchesData(
        username: String,
        repoName: String
    ): List<Branch> = coroutineScope{
        val branchList: MutableList<Branch> = mutableListOf()
        val response = WebClient.create().get()
            .uri("${apiConfiguration.url}/repos/$username/$repoName/branches")
            .headers { getUserAgentHeader() }
            .retrieve()
            .bodyToMono(List::class.java)
            .block()
        response?.forEach {
            launch{
                val responseMap = (it as LinkedHashMap<String, String>)
                val commit = (responseMap["commit"] as LinkedHashMap<String, String>)
                branchList.add(
                    Branch(
                        responseMap["name"]!!,
                        commit["sha"]!!
                    )
                )
            }
        }
        return@coroutineScope branchList
    }

    fun checkUserExists(username: String): Message? {
        return try {
            WebClient.create().get()
                .uri("${apiConfiguration.url}/users/$username")
                .headers { getUserAgentHeader() }
                .retrieve()
                .toBodilessEntity()
                .block()
            null
        } catch (ex: WebClientResponseException.NotFound){
            Message("404 Code", "User not found")
        }
    }

    fun getUserAgentHeader(): HttpHeaders {
        val headers = HttpHeaders()
        headers.add("User-Agent", apiConfiguration.agent)
        headers.add("Accept", "application/vnd.github+json")
        headers.add("Authorization",apiConfiguration.token)
        return headers
    }
}