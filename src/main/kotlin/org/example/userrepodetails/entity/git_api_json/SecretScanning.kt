package org.example.userrepodetails.entity.git_api_json

import com.google.gson.annotations.SerializedName


data class SecretScanning (
  @SerializedName("status") val status: String = ""
)