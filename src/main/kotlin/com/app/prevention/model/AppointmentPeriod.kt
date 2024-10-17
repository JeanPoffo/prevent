package com.app.prevention.model

enum class AppointmentPeriod(val value: String) {
    MORNING("Matutino"),
    AFTERNOON("Verspertino");

    override fun toString() = value
}