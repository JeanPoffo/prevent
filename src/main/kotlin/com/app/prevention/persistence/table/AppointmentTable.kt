package com.app.prevention.persistence.table

import com.app.prevention.model.AppointmentPeriod
import org.jetbrains.exposed.dao.id.UUIDTable
import org.jetbrains.exposed.sql.javatime.date

object AppointmentTable: UUIDTable("appointments") {
//    val patient = reference("patient", PatientTable)
    val date = date("date")
    val locality = varchar("locality", 100).nullable()
    val period = enumerationByName("period", 10, AppointmentPeriod::class).nullable()
}