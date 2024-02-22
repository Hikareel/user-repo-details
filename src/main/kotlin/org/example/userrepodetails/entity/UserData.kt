package org.example.userrepodetails.entity

import com.fasterxml.jackson.annotation.JsonProperty

data class UserData (
    @JsonProperty("Owner Login")
    val userLogin: String,
    @JsonProperty("Repos")
    val repositories: List<Repository>
)