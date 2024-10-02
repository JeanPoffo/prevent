package com.app.prevention.persistence.dao

import com.app.prevention.model.Patient
import com.app.prevention.persistence.table.PatientTable
import java.util.UUID
import org.jetbrains.exposed.dao.UUIDEntity
import org.jetbrains.exposed.dao.UUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class PatientDao(id: EntityID<UUID>) : UUIDEntity(id) {
    companion object : UUIDEntityClass<PatientDao>(PatientTable)

    var cpf by PatientTable.cpf
    var name by PatientTable.name
    var birthDate by PatientTable.birthDate
    var address by PatientTable.address
    var neighborhood by PatientTable.neighborhood
    var city by PatientTable.city
    var susNumber by PatientTable.susNumber

    fun toModel() = Patient(
        id = id.value,
        cpf = cpf,
        name = name,
        birthDate = birthDate,
        address = address,
        neighborhood = neighborhood,
        city = city,
        susNumber = susNumber
    )
}
