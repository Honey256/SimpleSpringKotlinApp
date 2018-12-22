package com.honeytech.simplespringkotlinapp.system.security.config


import com.honeytech.simplespringkotlinapp.model.UserRole
import com.honeytech.simplespringkotlinapp.system.security.service.CustomUserDetailsService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpStatus
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.password.NoOpPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler
import org.springframework.security.authentication.dao.DaoAuthenticationProvider
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.AuthenticationEntryPoint
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
class SecurityConfiguration : WebSecurityConfigurerAdapter() {

    @Autowired
    @Qualifier("restAuthenticationEntryPoint")
    lateinit var restAuthenticationEntryPoint: AuthenticationEntryPoint

    @Autowired
    @Qualifier("customSuccessHandler")
    lateinit var successHandler: SimpleUrlAuthenticationSuccessHandler

    @Autowired
    @Qualifier("customUserDetailsService")
    lateinit var userDetailsService: CustomUserDetailsService

    @Autowired
    @Qualifier("customPasswordEncoder")
    lateinit var encoder: CustomPasswordEncoder

    private val failureHandler = SimpleUrlAuthenticationFailureHandler()


    @Bean("authenticationManager")
    @Throws(Exception::class)
    override fun authenticationManagerBean(): AuthenticationManager {
        return super.authenticationManagerBean()
    }

    @Throws(Exception::class)
    override fun configure(auth: AuthenticationManagerBuilder?) {
        auth!!.authenticationProvider(authenticationProvider())
    }

    @Bean
    fun authenticationProvider(): DaoAuthenticationProvider {
        val authProvider = DaoAuthenticationProvider()
        authProvider.setUserDetailsService(userDetailsService)
        authProvider.setPasswordEncoder(encoder.encoder)
        return authProvider
    }

    @Bean
    fun noOpEncoder(): PasswordEncoder = NoOpPasswordEncoder.getInstance()

    @Bean
    fun cryptPasswordEncoder() : PasswordEncoder = BCryptPasswordEncoder()

    @Throws(Exception::class)
    override fun configure(http: HttpSecurity) {
        http
            .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
            .and()
            .csrf().disable()
            .exceptionHandling()
                .authenticationEntryPoint(restAuthenticationEntryPoint)
            .and()
            .authorizeRequests()
                .antMatchers("/api/login").permitAll()
                .antMatchers("/api/admin/**").hasRole(UserRole.ADMIN.toString())
                .anyRequest().authenticated()
            .and()
            .formLogin()
                .successHandler(successHandler)
                .failureHandler(failureHandler)
            .and()
            .httpBasic()
            .and()
            .logout()
                .logoutUrl("/api/logout").permitAll()
                .logoutSuccessHandler(HttpStatusReturningLogoutSuccessHandler(HttpStatus.OK))
    }
}



