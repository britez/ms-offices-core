package com.redbee.offices.application.service

import com.redbee.offices.application.port.out.EmployeeRepository
import com.redbee.offices.domain.Authority
import com.redbee.offices.domain.Employee
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.oauth2.jwt.Jwt
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken
import org.springframework.stereotype.Service

@Service
class EmployeeService(
    private val employeeRepository: EmployeeRepository
) {

    fun getAuthentication(): Employee {
        val jwt = (SecurityContextHolder.getContext().authentication.principal as Jwt)

        return employeeRepository
            .findByMail(jwt.getClaimAsString("email"))
            .let {
                it ?: employeeRepository.create(
                    Employee(
                        name = jwt.getClaimAsString(NAME),
                        givenName = jwt.getClaimAsString(GIVEN_NAME),
                        familyName = jwt.getClaimAsString(FAMILY_NAME),
                        mail = jwt.getClaimAsString(EMAIL),
                        picture = jwt.getClaimAsString(PICTURE),
                        authorities = setOf(Authority(role = USER)))
                ) }

    }

    companion object {
        private const val NAME:String = "name"
        private const val GIVEN_NAME:String = "givenName"
        private const val FAMILY_NAME:String = "familyName"
        private const val EMAIL:String = "email"
        private const val PICTURE:String = "picture"
        private const val USER:String = "USER"

    }

}