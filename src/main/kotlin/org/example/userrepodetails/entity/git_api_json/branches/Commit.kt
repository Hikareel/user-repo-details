package org.example.userrepodetails.entity.git_api_json.branches

import com.google.gson.annotations.SerializedName

data class Commit(
	@SerializedName("sha") val sha: String = "null",
	@SerializedName("url") val url: String = "null"
)