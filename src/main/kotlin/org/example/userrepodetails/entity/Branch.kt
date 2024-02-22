package org.example.userrepodetails.entity

import com.fasterxml.jackson.annotation.JsonProperty

data class Branch (
    @JsonProperty("Branch Name")
    val branchName: String,
    @JsonProperty("SHA")
    val lastCommitSha: String
)