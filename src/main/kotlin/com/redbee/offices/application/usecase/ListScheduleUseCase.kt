package com.redbee.offices.application.usecase

import com.redbee.offices.application.port.`in`.ListSchedulePortIn
import com.redbee.offices.application.port.out.ScheduleRepository
import com.redbee.offices.domain.Schedule
import org.springframework.stereotype.Component

@Component
class ListScheduleUseCase(
    val schedulerRepository: ScheduleRepository
): ListSchedulePortIn {
    override fun execute(): List<Schedule> =
        schedulerRepository.findAll()
}