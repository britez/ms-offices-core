package com.redbee.offices.application.port.`in`

import com.redbee.offices.domain.Office
import javax.validation.constraints.NotBlank

interface CreateOfficeCommand {

  fun execute(command:Command): Office

  data class Command(
    @get:NotBlank(message = "name should not be empty")
    val name: String,
    @get:NotBlank(message = "address should not be empty")
    var address:String,
    @get:NotBlank(message = "latitude should not be empty")
    var latitude:String,
    @get:NotBlank(message = "longitude should not be empty")
    var longitude:String)
}
