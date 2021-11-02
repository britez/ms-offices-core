package com.redbee.offices.application.usecase

import com.redbee.offices.application.port.`in`.GetSchedulePortIn
import com.redbee.offices.application.port.out.ScheduleRepository
import com.redbee.offices.domain.Schedule
import org.springframework.stereotype.Component

@Component
class GetScheduleUseCase(
    val schedulerRepository: ScheduleRepository
): GetSchedulePortIn {

    override fun execute(id: Long): Schedule =
        schedulerRepository
            .findById(id)
}