package com.app.prevention.model

import java.time.LocalDate
import java.util.UUID
import javafx.beans.property.SimpleObjectProperty
import javafx.beans.property.SimpleStringProperty

data class Appointment(
    val id: UUID,
    val patient: SimpleObjectProperty<Patient>,
    val date: SimpleObjectProperty<LocalDate>,
    val locality: SimpleStringProperty?,
    val period: SimpleObjectProperty<AppointmentPeriod>?,
) {
    constructor(
        id: UUID,
        patient: Patient,
        date: LocalDate,
        locality: String?,
        period: AppointmentPeriod?,
    ) : this(
        id,
        SimpleObjectProperty(patient),
        SimpleObjectProperty(date),
        SimpleStringProperty(locality),
        SimpleObjectProperty(period),
    )
}