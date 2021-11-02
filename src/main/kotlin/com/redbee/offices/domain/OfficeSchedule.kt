package com.redbee.offices.domain

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonTypeInfo
import javax.persistence.*

@JsonTypeInfo(
    use = JsonTypeInfo.Id.CLASS,
    include = JsonTypeInfo.As.PROPERTY,
    property = "@class")
@Entity
data class OfficeSchedule(
    @Id
    @org.springframework.data.annotation.Id
    @GeneratedValue
    @JsonProperty("id")
    val id:Long? = null,

    @JsonProperty("office")
    @ManyToOne
    val office: Office,

    @JsonProperty("availability")
    val availability: Long,

    @JsonProperty("employees")
    @ManyToMany(cascade = [CascadeType.ALL])
    val employees:MutableSet<Employee> = mutableSetOf(),

    @ManyToOne(cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    val schedule: Schedule
) {
    fun addEmployee(employee: Employee):OfficeSchedule {
        this.employees.add(employee);
        return this.copy(availability =  this.availability - 1)
    }
}