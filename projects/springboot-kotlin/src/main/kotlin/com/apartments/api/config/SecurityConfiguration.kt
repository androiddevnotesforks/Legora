package com.apartments.api.config


import com.apartments.api.autowired
import com.apartments.api.controllers.AccountsController
import com.apartments.api.service.AccountsService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.BeanIds
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter


@Configuration
@EnableWebSecurity
class SecurityConfiguration : WebSecurityConfigurerAdapter() {

    private val userDetailsService: AccountsService? by autowired()
    private val jwtFilter: JwtFilter by autowired()

    @Throws(Exception::class)
    override fun configure(auth: AuthenticationManagerBuilder) {
        auth.userDetailsService<UserDetailsService?>(userDetailsService)
    }

    @Bean(name = [BeanIds.AUTHENTICATION_MANAGER])
    @Throws(Exception::class)
    override fun authenticationManagerBean(): AuthenticationManager {
        return super.authenticationManagerBean()
    }

    @Bean
    fun encoder(): PasswordEncoder? {
        return BCryptPasswordEncoder()
    }

    @Throws(Exception::class)
    override fun configure(http: HttpSecurity) {
        http.csrf().disable().authorizeRequests()
            .antMatchers(AccountsController.ROUTER_NAME)
            .permitAll()
            .antMatchers(HttpMethod.POST, AccountsController.ROUTER_NAME + "/")
            .permitAll()
            .antMatchers(HttpMethod.POST, AccountsController.ROUTER_NAME + "/generate")
            .permitAll()
            .antMatchers(HttpMethod.GET, AccountsController.ROUTER_NAME + "/all")
            .permitAll()
            .antMatchers(HttpMethod.GET, "/" + AccountsController.ROUTER_NAME + "/all")
            .permitAll()
            .antMatchers(HttpMethod.POST, AccountsController.ROUTER_NAME + "/verify")
            .permitAll()
            .antMatchers(HttpMethod.POST, "/" + AccountsController.ROUTER_NAME + "/verify")
            .permitAll()
            .anyRequest()
            .authenticated()
            .and().sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter::class.java)

        http.exceptionHandling().authenticationEntryPoint(RestAuthenticationEntryPoint());
    }
}