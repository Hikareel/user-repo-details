package org.example.userrepodetails.entity.git_api_json.branches

import com.fasterxml.jackson.annotation.JsonProperty

data class Commit(
	@JsonProperty("sha") val sha: String = "null",
	@JsonProperty("url") val url: String = "null"
)