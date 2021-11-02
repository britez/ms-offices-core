package com.redbee.offices.application.usecase

import com.redbee.offices.application.port.`in`.ListOfficePortIn
import com.redbee.offices.application.port.out.OfficeRepository
import com.redbee.offices.domain.Office
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@Component
class ListOfficeUseCase(
  private var officeRepository: OfficeRepository
): ListOfficePortIn {

  private val log = LoggerFactory.getLogger(ListOfficeUseCase::class.java)

  override fun execute(): List<Office> =
    officeRepository
      .findAll()
}
