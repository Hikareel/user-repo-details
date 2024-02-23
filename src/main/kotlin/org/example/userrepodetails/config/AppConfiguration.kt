package org.example.userrepodetails.config

import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
@EnableConfigurationProperties(ApiConfiguration::class)
class AppConfiguration {
    @Bean
    fun apiConfiguration(): ApiConfiguration {
        return ApiConfiguration()
    }
}