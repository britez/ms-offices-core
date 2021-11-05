package com.redbee.offices.config

import com.redbee.offices.adapter.postgres.EmployeeAdapter
import com.redbee.offices.domain.Authority
import com.redbee.offices.domain.Employee
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.stereotype.Component

@Component
class AppStartRunner(
    private val employeeAdapter: EmployeeAdapter
):ApplicationRunner {

    override fun run(args: ApplicationArguments?) {
        Employee(
            name = "Maximiliano Britez",
            givenName = "Maxi",
            familyName = "Britez",
            mail = "maxi.britez@redb.ee",
            picture = "https://lh3.googleusercontent.com/a-/AOh14GixW8eJJarUkQQ2Jf8FoV1CkLJnl-JYJOcUtcJE1g=s96-c",
            authorities = setOf(Authority(role = "ADMIN"))
        ).let { employeeAdapter.create(it) }
    }
}