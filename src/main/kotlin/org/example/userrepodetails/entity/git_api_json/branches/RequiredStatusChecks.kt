package org.example.userrepodetails.entity.git_api_json.branches

import com.google.gson.annotations.SerializedName

data class RequiredStatusChecks(
    @SerializedName("enforcement_level")
    val enforcement_level: String = "null",
    @SerializedName("contexts")
    val contexts: List<String> = mutableListOf()
)
