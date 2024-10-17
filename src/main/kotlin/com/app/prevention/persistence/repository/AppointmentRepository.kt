package com.app.prevention.persistence.repository

import com.app.prevention.model.Appointment
import com.app.prevention.persistence.dao.AppointmentDao
import com.app.prevention.persistence.dao.PatientDao
import org.jetbrains.exposed.sql.transactions.transaction

class AppointmentRepository {
    fun findAll(): List<Appointment> = transaction {
        AppointmentDao.all().sortedByDescending { it.date }.map { it.toModel() }
    }

    fun delete(appointment: Appointment) {
        transaction {
            AppointmentDao.findById(appointment.id!!)?.delete()
        }
    }

    fun save(appointment: Appointment) {
        transaction {
            AppointmentDao.new {
                patient = PatientDao.findById(appointment.patient.get().id!!)!!
                date = appointment.date.value
                locality = appointment.locality?.value
                period = appointment.period?.value
            }
        }
    }

    fun update(appointment: Appointment) {
        transaction {
            AppointmentDao.findByIdAndUpdate(appointment.id!!) {
                it.patient = PatientDao.findById(appointment.patient.get().id!!)!!
                it.date = appointment.date.value
                it.locality = appointment.locality?.value
                it.period = appointment.period?.value
            }
        }
    }
}