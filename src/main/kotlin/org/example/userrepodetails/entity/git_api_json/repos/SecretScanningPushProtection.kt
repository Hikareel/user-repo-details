package org.example.userrepodetails.entity.git_api_json.repos

import com.fasterxml.jackson.annotation.JsonProperty

data class SecretScanningPushProtection (
  @JsonProperty("status") val status: String = ""
)