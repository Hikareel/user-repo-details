package org.example.userrepodetails.controller

import org.example.userrepodetails.entity.UserData
import org.example.userrepodetails.service.GitHubService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController

@RestController
class MainController(
    val gitHubService: GitHubService
) {

    @GetMapping("/repos")
    @ResponseBody
    fun repos(@RequestParam username: String): String? {
        return gitHubService.getUserData(username)
    }
}