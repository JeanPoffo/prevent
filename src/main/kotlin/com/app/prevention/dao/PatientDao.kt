package com.app.prevention.dao

import com.app.prevention.dao.PatientDao.address
import com.app.prevention.dao.PatientDao.birthDate
import com.app.prevention.dao.PatientDao.city
import com.app.prevention.dao.PatientDao.cpf
import com.app.prevention.dao.PatientDao.name
import com.app.prevention.dao.PatientDao.neighborhood
import com.app.prevention.dao.PatientDao.susNumber
import com.app.prevention.model.Patient
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.javatime.date
import org.jetbrains.exposed.sql.transactions.transaction
import org.jetbrains.exposed.sql.update

object PatientDao: IntIdTable("patients") {
    val cpf = varchar("cpf", 11).uniqueIndex()
    val name = varchar("name", 100)
    val birthDate = date("birth_date")
    val address = varchar("address", 100)
    val neighborhood = varchar("neighborhood", 100)
    val city = varchar("city", 100)
    val susNumber = varchar("sus_number", 15)
}

fun PatientDao.insert(patient: Patient) = transaction {
    this@insert.insert {
        it[cpf] = patient.cpf.get()
        it[name] = patient.name.get()
        it[birthDate] = patient.birthDate.get()
        it[address] = patient.address.get()
        it[neighborhood] = patient.neighborhood.get()
        it[city] = patient.city.get()
        it[susNumber] = patient.susNumber.get()
    }
}

fun PatientDao.update(patient: Patient) = transaction {
    if (patient.id != null) {
        this@update.update({ PatientDao.id eq patient.id }) {
            it[cpf] = patient.cpf.get()
            it[name] = patient.name.get()
            it[birthDate] = patient.birthDate.get()
            it[address] = patient.address.get()
            it[neighborhood] = patient.neighborhood.get()
            it[city] = patient.city.get()
            it[susNumber] = patient.susNumber.get()
        }
    } else {
        error("Missing Patient ID")
    }
}

fun PatientDao.delete(patient: Patient) = transaction {
    if (patient.id != null) {
        this@delete.deleteWhere { PatientDao.id eq patient.id }
    } else {
        error("Missing Patient ID")
    }
}

fun ResultRow.toPatientModel() = Patient(
    this[PatientDao.id].value,
    this[cpf],
    this[name],
    this[birthDate],
    this[address],
    this[neighborhood],
    this[city],
    this[susNumber]
)