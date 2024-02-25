package org.example.userrepodetails.entity.git_api_json.branches

import com.fasterxml.jackson.annotation.JsonProperty

data class BranchJson(
    @JsonProperty("name") val name: String = "null",
    @JsonProperty("commit") val commit: Commit = Commit(),
    @JsonProperty("protected") val protected: Boolean = false,
    @JsonProperty("protection") val protection: Protection = Protection(),
    @JsonProperty("protection_url") val protectionUrl: String = "null",
)