package org.example.userrepodetails.entity.git_api_json.branches

import com.google.gson.annotations.SerializedName

data class BranchJson (
    @SerializedName("name") val name: String = "null",
    @SerializedName("commit") val commit: Commit = Commit(),
    @SerializedName("protected") val protected: Boolean = false,
    @SerializedName("protection") val protection: Protection = Protection(),
    @SerializedName("protection_url") val protection_url: String = "null",
)