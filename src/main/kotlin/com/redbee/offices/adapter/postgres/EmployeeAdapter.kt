package com.redbee.offices.adapter.postgres

import com.redbee.offices.application.port.out.EmployeeRepository
import com.redbee.offices.domain.Employee
import org.springframework.stereotype.Repository
import java.util.*

@Repository
class EmployeeAdapter(
  private val employeeRepository: EmployeeJPARepository
): EmployeeRepository {

  override fun create(employee: Employee): Employee =
    employeeRepository.save(employee)

  override fun findByMail(it: String): Employee? =
    employeeRepository.findByMail(it).orElse(null)

  companion object {
  }

}
