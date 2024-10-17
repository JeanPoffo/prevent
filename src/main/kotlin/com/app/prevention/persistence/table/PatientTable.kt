package com.app.prevention.persistence.table

import org.jetbrains.exposed.dao.id.UUIDTable
import org.jetbrains.exposed.sql.javatime.date

object PatientTable: UUIDTable("patients") {
    val cpf = varchar("cpf", 11).uniqueIndex()
    val name = varchar("name", 100)
    val birthDate = date("birth_date")
    val address = varchar("address", 100)
    val neighborhood = varchar("neighborhood", 100)
    val city = varchar("city", 100)
    val susNumber = varchar("sus_number", 15)
}
