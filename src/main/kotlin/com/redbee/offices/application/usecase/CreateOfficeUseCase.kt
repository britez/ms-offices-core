package com.redbee.offices.application.usecase

import com.redbee.offices.application.port.`in`.CreateOfficeCommand
import com.redbee.offices.application.port.out.OfficeRepository
import com.redbee.offices.domain.Location
import com.redbee.offices.domain.Office
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@Component
class CreateOfficeUseCase(
  var officeRepository: OfficeRepository
): CreateOfficeCommand {

  private val log = LoggerFactory.getLogger(CreateOfficeUseCase::class.java)

  override fun execute(command: CreateOfficeCommand.Command): Office =
    officeRepository
      .also { log.info("Attempt to create office {}", command) }
      .create(Office(
        name = command.name,
        address = command.address,
        location = Location(longitude = command.longitude, latitude = command.latitude)
      ))
      .also { log.info("Office created {}", it) }
}
