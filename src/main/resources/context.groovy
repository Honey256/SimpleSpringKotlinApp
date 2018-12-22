import com.honeytech.simplespringkotlinapp.system.security.config.CustomPasswordEncoder
import org.springframework.jdbc.datasource.DriverManagerDataSource

beans {

    //признак включенности шифрования пароля
    def isEncodingEnabled = false

    dataSource(DriverManagerDataSource) {
        bean ->
            bean.scope = "singleton"
        driverClassName = "org.postgresql.Driver"
        url = "jdbc:postgresql://localhost:5432/postgres"
        username="postgres"
        password="password"
    }

    customPasswordEncoder(CustomPasswordEncoder) { bean->
        if (isEncodingEnabled) {
            encoder = ref('cryptPasswordEncoder')
        } else {
            encoder = ref('noOpEncoder')
        }
    }
}