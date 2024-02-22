package org.example.userrepodetails.config

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "git")
data class ApiConfiguration(
    var url: String? = "",
    var agent: String? = "",
    var token: String? = ""
)