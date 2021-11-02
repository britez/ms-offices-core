package com.redbee.offices.application.port.`in`

import com.redbee.offices.domain.Office

interface ListOfficePortIn {

  fun execute(): List<Office>

}