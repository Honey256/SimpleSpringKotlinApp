import com.honeytech.simplespringkotlinapp.system.security.config.CustomSavedRequestAwareAuthenticationSuccessHandler
import com.honeytech.simplespringkotlinapp.system.security.config.RestAuthenticationEntryPoint
import com.honeytech.simplespringkotlinapp.system.security.service.CustomUserDetailsService
import org.springframework.jdbc.datasource.DriverManagerDataSource

beans {

    dataSource(DriverManagerDataSource) {
        driverClassName = "org.postgresql.Driver"
        url = "jdbc:postgresql://localhost:5432/postgres"
        username="postgres"
        password="password"

    }

    customSuccessHandler(CustomSavedRequestAwareAuthenticationSuccessHandler)

    restAuthenticationEntryPoint(RestAuthenticationEntryPoint)

    customUserDetailsService(CustomUserDetailsService)

}