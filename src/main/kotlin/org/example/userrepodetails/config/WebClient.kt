package org.example.userrepodetails.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.client.WebClient

@Configuration
class WebClient {
	@Bean
	fun WebClient(): WebClient {
		return WebClient.create()
	}
}