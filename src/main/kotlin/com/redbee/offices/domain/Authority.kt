package com.redbee.offices.domain

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonTypeInfo
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@JsonTypeInfo(
    use = JsonTypeInfo.Id.CLASS,
    include = JsonTypeInfo.As.PROPERTY,
    property = "@class")
@Entity
data class Authority(
    @Id
    @org.springframework.data.annotation.Id
    @GeneratedValue
    @JsonProperty("id")
    val id:Long? = null,
    @JsonProperty("role")
    val role: String,
)
