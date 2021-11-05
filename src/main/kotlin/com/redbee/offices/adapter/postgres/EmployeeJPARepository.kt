package com.redbee.offices.adapter.postgres

import com.redbee.offices.domain.Employee
import com.redbee.offices.domain.Office
import org.springframework.data.repository.CrudRepository
import java.util.*

interface EmployeeJPARepository:CrudRepository<Employee, Long> {
    fun findByMail(it: String): Optional<Employee>
}
