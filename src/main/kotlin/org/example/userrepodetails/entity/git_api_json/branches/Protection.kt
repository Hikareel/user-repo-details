package org.example.userrepodetails.entity.git_api_json.branches

import com.fasterxml.jackson.annotation.JsonProperty

data class Protection(
    @JsonProperty("required_status_checks")
    val requiredStatusChecks: RequiredStatusChecks = RequiredStatusChecks()
)
