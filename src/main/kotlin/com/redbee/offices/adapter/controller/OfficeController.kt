package com.redbee.offices.adapter.controller

import com.redbee.offices.adapter.controller.domain.OfficeResponse
import com.redbee.offices.application.port.`in`.CreateOfficeCommand
import com.redbee.offices.application.usecase.ListOfficeUseCase
import org.springframework.http.HttpStatus
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@RequestMapping("/offices")
@RestController
class OfficeController(
    val createOfficeCommand: CreateOfficeCommand,
    val listOfficesUseCase: ListOfficeUseCase
    ) {

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    fun create(@Validated @RequestBody command: CreateOfficeCommand.Command): OfficeResponse =
        createOfficeCommand
            .execute(command)
            .let { OfficeResponse(
                it.id!!,
                it.name,
                it.address,
                OfficeResponse.LocationResponse(it.location.longitude, it.location.latitude)
            ) }

    @GetMapping
    fun list() =
        listOfficesUseCase
            .execute()
            .map { OfficeResponse(
                it.id!!,
                it.name,
                it.address,
                OfficeResponse.LocationResponse(it.location.longitude, it.location.latitude)) }
}