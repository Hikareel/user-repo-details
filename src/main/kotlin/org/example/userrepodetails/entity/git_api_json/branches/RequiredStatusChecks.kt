package org.example.userrepodetails.entity.git_api_json.branches

import com.fasterxml.jackson.annotation.JsonProperty

data class RequiredStatusChecks(
    @JsonProperty("enforcement_level")
    val enforcementLevel: String = "null",
    @JsonProperty("contexts")
    val contexts: List<String> = mutableListOf()
)
