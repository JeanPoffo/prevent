package com.app.prevention.model

import java.time.LocalDateTime
import javafx.beans.property.SimpleObjectProperty
import javafx.beans.property.SimpleStringProperty

data class Schedule(
    val id: Int? = null,
    val transporter: SimpleStringProperty,
    val dateTime: SimpleObjectProperty<LocalDateTime>,
) {
    constructor(
        id: Int? = null,
        transporter: String,
        dateTime: LocalDateTime,
    ) : this(
        id,
        SimpleStringProperty(transporter),
        SimpleObjectProperty(dateTime),
    )
}
