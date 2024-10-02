package com.app.prevention.persistence.dao

import com.app.prevention.model.Appointment
import com.app.prevention.persistence.table.AppointmentTable
import java.util.UUID
import org.jetbrains.exposed.dao.UUIDEntity
import org.jetbrains.exposed.dao.UUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class AppointmentDao(id: EntityID<UUID>) : UUIDEntity(id) {
    companion object : UUIDEntityClass<AppointmentDao>(AppointmentTable)

//    var patient by AppointmentDao referencedOn AppointmentTable.patient
    var date by AppointmentTable.date
    var locality by AppointmentTable.locality
    var period by AppointmentTable.period

//    fun toAppointment() = Appointment(
//        id = id.value,
//        patient = patient,
//        date = date,
//        locality = locality,
//        period = period
//    )
}