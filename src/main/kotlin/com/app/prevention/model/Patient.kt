package com.app.prevention.model

import java.time.LocalDate
import javafx.beans.property.SimpleObjectProperty
import javafx.beans.property.SimpleStringProperty
import javafx.beans.property.StringProperty

data class Patient(
    val cpf: StringProperty,
    val name: StringProperty,
    val birthDate: SimpleObjectProperty<LocalDate>,
    val address: StringProperty,
    val neighborhood: StringProperty,
    val city: StringProperty,
    val susNumber: StringProperty,
) {
    constructor(
        cpf: String,
        name: String,
        birthDate: LocalDate,
        address: String,
        neighborhood: String,
        city: String,
        susNumber: String
    ) : this(
        SimpleStringProperty(cpf),
        SimpleStringProperty(name),
        SimpleObjectProperty<LocalDate>(birthDate),
        SimpleStringProperty(address),
        SimpleStringProperty(neighborhood),
        SimpleStringProperty(city),
        SimpleStringProperty(susNumber)
    )
}