package org.example.userrepodetails.entity

import com.google.gson.annotations.SerializedName

data class UserData (
    @SerializedName("Owner Login")
    val userLogin: String,
    @SerializedName("Repos")
    val repositories: List<Repository>
)