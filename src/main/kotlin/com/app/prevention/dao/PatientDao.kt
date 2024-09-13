package com.app.prevention.dao

import com.app.prevention.model.Patient
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.javatime.date
import org.jetbrains.exposed.sql.transactions.transaction

object PatientDao: IntIdTable() {
    val cpf = varchar("cpf", 11).uniqueIndex()
    val name = varchar("name", 100)
    val birthDate = date("birth_date")
    val address = varchar("address", 100)
    val neighborhood = varchar("neighborhood", 100)
    val city = varchar("city", 100)
    val susNumber = varchar("sus_number", 15)
}

fun Patient.insertDatabase() = transaction {
    PatientDao.insert {
        it[cpf] = this@insertDatabase.cpf.get()
        it[name] = this@insertDatabase.name.get()
        it[birthDate] = this@insertDatabase.birthDate.get()
        it[address] = this@insertDatabase.address.get()
        it[neighborhood] = this@insertDatabase.neighborhood.get()
        it[city] = this@insertDatabase.city.get()
        it[susNumber] = this@insertDatabase.susNumber.get()
    }
}

fun ResultRow.toPatientModel() = Patient(
    this[PatientDao.cpf],
    this[PatientDao.name],
    this[PatientDao.birthDate],
    this[PatientDao.address],
    this[PatientDao.neighborhood],
    this[PatientDao.city],
    this[PatientDao.susNumber]
)