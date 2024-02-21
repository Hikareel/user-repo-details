package org.example.userrepodetails.entity

import com.fasterxml.jackson.annotation.JsonProperty

class UserData {
    @JsonProperty("Owner Login")
    var user_login: String? = null
    @JsonProperty("Repos")
    var repositories: List<Repository>? = null
}