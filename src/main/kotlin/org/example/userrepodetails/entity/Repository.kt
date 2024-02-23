package org.example.userrepodetails.entity

import com.google.gson.annotations.SerializedName

data class Repository (
    @SerializedName("Repository Name")
    val repoName: String,
    @SerializedName("Branches")
    val branches: List<Branch>
)