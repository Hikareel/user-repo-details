package org.example.userrepodetails.entity.git_api_json.repos

import com.fasterxml.jackson.annotation.JsonProperty

data class Permissions(
	@JsonProperty("admin") val admin: Boolean = false,
	@JsonProperty("push") val push: Boolean = false,
	@JsonProperty("pull") val pull: Boolean = false
)