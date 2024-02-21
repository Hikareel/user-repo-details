package org.example.userrepodetails.controller

import org.example.userrepodetails.entity.UserData
import org.example.userrepodetails.service.GitHubService
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam

@Controller
class MainController(
    val gitHubService: GitHubService
) {

    @GetMapping("/repos")
    fun repos(@RequestParam username: String): Any {
//        try {
            return ResponseEntity.ok(gitHubService.getUserData(username))
//        } catch (error: Exception){
//            return ResponseEntity.badRequest()
//        }

    }
}