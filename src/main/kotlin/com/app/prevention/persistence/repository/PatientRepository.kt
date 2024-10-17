package com.app.prevention.persistence.repository

import com.app.prevention.model.Patient
import com.app.prevention.persistence.dao.PatientDao
import org.jetbrains.exposed.sql.transactions.transaction

class PatientRepository {
    fun findAll(): List<Patient> = transaction {
        PatientDao.all().map { it.toModel() }
    }

    fun delete(patient: Patient) {
        transaction {
            PatientDao.findById(patient.id!!)?.delete()
        }
    }

    fun save(patient: Patient) {
        transaction {
            PatientDao.new {
                cpf = patient.cpf.value
                name = patient.name.value
                birthDate = patient.birthDate.value
                address = patient.address.value
                neighborhood = patient.neighborhood.value
                city = patient.city.value
                susNumber = patient.susNumber.value
            }
        }
    }

    fun update(patient: Patient) {
        transaction {
            PatientDao.findByIdAndUpdate(patient.id!!) {
                it.cpf = patient.cpf.value
                it.name = patient.name.value
                it.birthDate = patient.birthDate.value
                it.address = patient.address.value
                it.neighborhood = patient.neighborhood.value
                it.city = patient.city.value
                it.susNumber = patient.susNumber.value
            }
        }
    }
}