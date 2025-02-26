package measuremanager.iam_module
/*
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.boot.jdbc.DataSourceBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import javax.sql.DataSource

@Configuration
class DataSourceConfig {
    @Qualifier("PostgresDataSource")
    @Bean
    fun dataSource(): DataSource {
        return DataSourceBuilder.create()
            .url("jdbc:postgresql://172.20.0.20:5432/mydatabase")
            .username("myuser")
            .password("secret")
            .build()
    }

    @Primary
    @Bean
    fun keycloakDataSource(): DataSource {
        return DataSourceBuilder.create()
            .url("jdbc:postgresql://172.20.0.21:5432/keycloak")
            .username("keycloak")
            .password("keycloakpassword")
            .build()
    }
}


 */