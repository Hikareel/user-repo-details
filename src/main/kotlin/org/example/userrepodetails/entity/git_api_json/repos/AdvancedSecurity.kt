package org.example.userrepodetails.entity.git_api_json.repos

import com.fasterxml.jackson.annotation.JsonProperty

data class AdvancedSecurity(
    @JsonProperty("status") val status: String = ""
)