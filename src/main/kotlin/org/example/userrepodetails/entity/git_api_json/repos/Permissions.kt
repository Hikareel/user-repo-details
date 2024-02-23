package org.example.userrepodetails.entity.git_api_json.repos

import com.google.gson.annotations.SerializedName


data class Permissions (
  @SerializedName("admin") val admin: Boolean = false,
  @SerializedName("push") val push: Boolean = false,
  @SerializedName("pull") val pull: Boolean = false
)