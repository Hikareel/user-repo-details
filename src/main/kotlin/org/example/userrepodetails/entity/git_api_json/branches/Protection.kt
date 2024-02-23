package org.example.userrepodetails.entity.git_api_json.branches

import com.google.gson.annotations.SerializedName

data class Protection(
    @SerializedName("required_status_checks")
    val required_status_checks: RequiredStatusChecks = RequiredStatusChecks()
)
