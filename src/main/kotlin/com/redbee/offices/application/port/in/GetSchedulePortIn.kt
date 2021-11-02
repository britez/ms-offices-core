package com.redbee.offices.application.port.`in`

import com.redbee.offices.domain.Schedule

interface GetSchedulePortIn {

    fun execute(id:Long): Schedule

}
