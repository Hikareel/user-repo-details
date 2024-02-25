package org.example.userrepodetails.entity.git_api_json.repos

import com.fasterxml.jackson.annotation.JsonProperty

data class SecurityAndAnalysis (
    @JsonProperty("advanced_security") val advancedSecurity: AdvancedSecurity = AdvancedSecurity(),
    @JsonProperty("secret_scanning") val secretScanning: SecretScanning = SecretScanning(),
    @JsonProperty("secret_scanning_push_protection") val secretScanningPushProtection: SecretScanningPushProtection = SecretScanningPushProtection()
)