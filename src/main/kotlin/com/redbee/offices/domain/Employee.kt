package com.redbee.offices.domain

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonTypeInfo
import javax.persistence.*

@JsonTypeInfo(
    use = JsonTypeInfo.Id.CLASS,
    include = JsonTypeInfo.As.PROPERTY,
    property = "@class")
@Entity
data class Employee(
    @Id
    @org.springframework.data.annotation.Id
    @GeneratedValue
    @JsonProperty("id")
    val id:Long? = null,
    @JsonProperty("mail")
    val mail: String,
    @JsonProperty("name")
    val name: String,
    @JsonProperty("givenName")
    val givenName:String,
    @JsonProperty("familyName")
    val familyName:String,
    @JsonProperty("picture")
    val picture:String,
    @ManyToMany(cascade = [CascadeType.ALL], fetch = FetchType.EAGER)
    val authorities:Set<Authority>,
)
