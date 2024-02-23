package org.example.userrepodetails.entity.git_api_json

import com.google.gson.annotations.SerializedName


data class SecurityAndAnalysis (
    @SerializedName("advanced_security") val advancedSecurity: AdvancedSecurity = AdvancedSecurity(),
    @SerializedName("secret_scanning") val secretScanning: SecretScanning = SecretScanning(),
    @SerializedName("secret_scanning_push_protection") val secretScanningPushProtection: SecretScanningPushProtection = SecretScanningPushProtection()
)