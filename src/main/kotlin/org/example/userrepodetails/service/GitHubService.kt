package org.example.userrepodetails.service

import com.fasterxml.jackson.databind.ObjectMapper
import org.example.userrepodetails.entity.UserData
import org.springframework.http.*
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import java.util.Objects

@Service
class GitHubService(
    val objectMapper: ObjectMapper
) {
    fun getUserData(username: String): UserData {
        if(!checkUserExists(username)){
            throw Exception("User not found")
        }
        val user: UserData = getUserReposData(username)


        return user
    }

    fun getUserReposData(username: String): UserData {
        val user = UserData()
        user.user_login = username
        val response = RestTemplate().exchange(
            "https://api.github.com/users/$username/repos",
            HttpMethod.GET,
            getRequestEntity(),
            String::class.java
        )
        println(response)
        return user
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
        } catch (error: Exception){
            return false
        }
    }

    fun getRequestEntity(): HttpEntity<String> {
        return HttpEntity<String>(getUserAgentHeader())
    }

    fun getUserAgentHeader(): HttpHeaders {
        val headers: HttpHeaders = HttpHeaders()
        headers.add("User-Agent", "http://developer.github.com/v3/Hikareel")
        headers.add("Accept", "application/vnd.github+json")
        return headers
    }
}