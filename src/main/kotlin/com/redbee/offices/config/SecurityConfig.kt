package com.redbee.offices.config

import com.redbee.offices.application.port.out.EmployeeRepository
import com.redbee.offices.domain.Authority
import com.redbee.offices.domain.Employee
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfiguration
import org.springframework.security.oauth2.core.DelegatingOAuth2TokenValidator
import org.springframework.security.oauth2.core.OAuth2Error
import org.springframework.security.oauth2.core.OAuth2TokenValidator
import org.springframework.security.oauth2.core.OAuth2TokenValidatorResult
import org.springframework.security.oauth2.jwt.*
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken
import org.springframework.stereotype.Component

@Configuration
class SecurityConfig: ResourceServerConfiguration() {

    override fun configure(http: HttpSecurity?) {
        http!!
            .authorizeRequests()
            .antMatchers(HttpMethod.POST, "/offices").hasRole("ADMIN")
            .antMatchers(HttpMethod.POST, "/schedules").hasRole("ADMIN")
            .antMatchers(HttpMethod.PUT, "/schedules").hasRole("ADMIN")
            .antMatchers(HttpMethod.GET, "/user/info").hasRole("ADMIN")
            .anyRequest().authenticated()
            .and().oauth2ResourceServer().jwt();
    }

    @Bean
    fun getJwtDecoder():JwtDecoder {
        val decoder = NimbusJwtDecoder
            .withJwkSetUri("https://www.googleapis.com/oauth2/v3/certs")
            .build()

        decoder.setJwtValidator(DelegatingOAuth2TokenValidator<Jwt>(
            JwtIssuerValidator("https://accounts.google.com"),
            JwtTimestampValidator(),
            DomainValidator()
        ))

        return decoder;
    }

    class DomainValidator:OAuth2TokenValidator<Jwt> {

        override fun validate(token: Jwt?): OAuth2TokenValidatorResult  {
            if(token == null) {
                return OAuth2TokenValidatorResult.failure(OAuth2Error("token must be present"))
            }

            return if (token
                .getClaim<String>(HD)
                .equals(HD_VALUE)) {
                OAuth2TokenValidatorResult.success() }
            else OAuth2TokenValidatorResult.failure(OAuth2Error("hd must be redb.ee"))
        }

        companion object {
            const val HD:String = "hd"
            const val HD_VALUE:String = "redb.ee"
        }

    }

    @Component
    class CustomTokenConverter(
        private val employeeRepository: EmployeeRepository
    ): JwtAuthenticationConverter() {

        override fun extractAuthorities(jwt: Jwt?): MutableCollection<GrantedAuthority> {
            return jwt
            .let{ it?.getClaimAsString("email")
                ?: throw JwtValidationException("error with token", listOf(OAuth2Error("email"))) }
            .let { employeeRepository.findByMail(it) }
            .let { it?.authorities?.map { au -> SimpleGrantedAuthority("ROLE_${au.role}") } ?: setOf(SimpleGrantedAuthority("USER")) }
            .toMutableSet()
        }


    }

}