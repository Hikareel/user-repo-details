package org.example.userrepodetails.service

import org.example.userrepodetails.entity.Branch
import org.example.userrepodetails.entity.Repository
import org.example.userrepodetails.entity.UserData
import org.springframework.http.*
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import org.springframework.web.reactive.function.client.WebClient
import java.util.LinkedHashMap

@Service
class GitHubService {
    fun getUserData(username: String): Any {
        if(!checkUserExists(username)){
            return ResponseEntity.notFound()
        }
        val user = UserData()
        user.user_login = username
        user.repositories = getReposData(username)
        return ResponseEntity.ok(user)
    }

    fun getReposData(username: String): List<Repository> {
        var repoList: MutableList<Repository> = mutableListOf()
        val response = WebClient.create().get()
            .uri("https://api.github.com/users/$username/repos")
            .headers { getUserAgentHeader() }
            .retrieve()
            .bodyToMono(List::class.java)
            .block()
        response?.forEach{
            val responseMap = (it as LinkedHashMap<String, String>)
            if (responseMap["fork"].toString() == "false"){
                val repository = Repository()
                repository.rp_name = responseMap["name"]
                repository.branches = responseMap["name"]?.let { it1 -> getRepoBranchesData(username, it1) }
                repoList.add(repository)
            }
        }
        return repoList
    }

    fun getRepoBranchesData(username: String, repoName: String): List<Branch>{
        var branchList: MutableList<Branch> = mutableListOf()
        val response = WebClient.create().get()
            .uri("https://api.github.com/repos/$username/$repoName/branches")
            .headers { getUserAgentHeader() }
            .retrieve()
            .bodyToMono(List::class.java)
            .block()
        response?.forEach {
            val responseMap = (it as LinkedHashMap<String, String>)
            val branch = Branch()
            branch.br_name = responseMap["name"]
            val commit = (responseMap["commit"] as LinkedHashMap<String, String>)
            branch.last_commit_sha = commit["sha"]
            branchList.add(branch)
        }
        return branchList
    }

    fun checkUserExists(username: String): Boolean {
        try {
            val response = RestTemplate().exchange(
                "https://api.github.com/users/$username",
                HttpMethod.GET,
                getRequestEntity(),
                String::class.java
            )
            return true
//            val response = WebClient.create().get()
//                .uri("https://api.github.com/users/$username")
//                .headers { getUserAgentHeader() }

        } catch (error: Exception){
            return false
        }
    }

    fun getRequestEntity(): HttpEntity<String> {
        return HttpEntity<String>(getUserAgentHeader())
    }

    fun getUserAgentHeader(): HttpHeaders {
        val headers = HttpHeaders()
        headers.add("User-Agent", "https://api.github.com/users/Hikareel")
        headers.add("Accept", "application/vnd.github+json")
        headers.add("Authorization","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")
        return headers
    }
}