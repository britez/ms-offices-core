package com.redbee.offices.application.usecase

import com.redbee.offices.application.port.`in`.UpdateScheduleCommand
import com.redbee.offices.application.port.out.ScheduleRepository
import com.redbee.offices.domain.Schedule
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@Component
class UpdateScheduleUseCase(
  var scheduleRepository: ScheduleRepository
): UpdateScheduleCommand {

  private val log = LoggerFactory.getLogger(UpdateScheduleUseCase::class.java)

  override fun execute(id: Long, command: UpdateScheduleCommand.Command): Schedule =
    log
      .info("Attempt to find schedule with id $id")
      .let { scheduleRepository.findById(id) }
//      .also { log.info("Schedule found $it")  }
      .copy(day = command.day, state = command.state)
//      .also{ log.info("Attempt to update schedule $it") }
      .let { scheduleRepository.update(it) }
//      .also { log.info("Schedule updated successfully $it") }

}
