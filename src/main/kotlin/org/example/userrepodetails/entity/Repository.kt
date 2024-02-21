package org.example.userrepodetails.entity

import com.fasterxml.jackson.annotation.JsonProperty

class Repository {
    @JsonProperty("Repository Name")
    var rp_name: String? = null
    @JsonProperty("Branches")
    var branches: List<Branch>? = null
}