package com.redbee.offices.application.port.out

import com.redbee.offices.domain.Employee

interface EmployeeRepository {
  fun create(employee: Employee): Employee
  fun findByMail(it: String): Employee?

  companion object {

  }
}
