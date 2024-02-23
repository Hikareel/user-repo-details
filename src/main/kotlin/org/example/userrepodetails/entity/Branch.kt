package org.example.userrepodetails.entity

import com.google.gson.annotations.SerializedName

data class Branch (
    @SerializedName("Branch Name")
    val branchName: String,
    @SerializedName("SHA")
    val lastCommitSha: String
)