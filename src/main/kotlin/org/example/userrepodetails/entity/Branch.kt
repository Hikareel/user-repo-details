package org.example.userrepodetails.entity

import com.fasterxml.jackson.annotation.JsonProperty

class Branch {
    @JsonProperty("Branch Name")
    var br_name: String? = null
    @JsonProperty("SHA")
    var last_commit_sha: String? = null
}