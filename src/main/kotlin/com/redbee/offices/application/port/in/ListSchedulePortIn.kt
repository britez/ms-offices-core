package com.redbee.offices.application.port.`in`

import com.redbee.offices.domain.Schedule

interface ListSchedulePortIn {

    fun execute():List<Schedule>

}
