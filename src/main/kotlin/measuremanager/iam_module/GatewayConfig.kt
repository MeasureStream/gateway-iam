package measuremanager.iam_module

import org.apache.http.client.HttpClient
import org.apache.http.impl.client.HttpClients
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory
import org.springframework.web.client.RestTemplate

/*
@Configuration
class GatewayConfig {

    @Bean
    fun customHttpClient(): HttpClient {
        // Configura Apache HttpClient
        return HttpClients.custom()
            .setMaxConnTotal(200)
            .setMaxConnPerRoute(20)
            .build()
    }


}*/