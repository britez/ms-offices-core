package com.redbee.offices.domain

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonTypeInfo
import java.time.LocalDateTime
import javax.persistence.*

@JsonTypeInfo(
    use = JsonTypeInfo.Id.CLASS,
    include = JsonTypeInfo.As.PROPERTY,
    property = "@class")
@Entity
data class Schedule(
    @Id
    @org.springframework.data.annotation.Id
    @GeneratedValue
    @JsonProperty("id")
    val id:Long? = null,

    @JsonProperty("day")
    val day:LocalDateTime,

    @JsonProperty("state")
    val state:ScheduleState,

    @OneToMany(
        mappedBy = "schedule",
        fetch = FetchType.LAZY)
    val officeSchedule: MutableList<OfficeSchedule>? = mutableListOf()
) {
    private fun addOfficeSchedule(officeSchedule: OfficeSchedule) {
        this.officeSchedule?.add(officeSchedule)
    }

    fun addOfficeSchedule(list: List<OfficeSchedule>):Schedule {
        list.map { this.addOfficeSchedule(it) }
        return this;
    }
}