package com.app.prevention.model

data class Appointment(
    val id: Int? = null,
    val patient: Patient,
    val schedule: Schedule,
)
