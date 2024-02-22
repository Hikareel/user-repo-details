package org.example.userrepodetails.entity

import com.fasterxml.jackson.annotation.JsonProperty

data class Repository (
    @JsonProperty("Repository Name")
    val repoName: String,
    @JsonProperty("Branches")
    val branches: List<Branch>
)