package com.app.prevention.controller

import com.app.prevention.model.Appointment
import com.app.prevention.model.AppointmentPeriod
import com.app.prevention.model.Patient
import com.app.prevention.persistence.repository.AppointmentRepository
import com.app.prevention.persistence.repository.PatientRepository
import com.app.prevention.util.informationMessage
import java.net.URL
import java.util.ResourceBundle
import javafx.fxml.FXML
import javafx.fxml.Initializable
import javafx.scene.control.CheckBox
import javafx.scene.control.ComboBox
import javafx.scene.control.DatePicker
import javafx.scene.control.TextField
import javafx.scene.layout.HBox

class AppointmentEditController: Initializable {

    private val appointmentRepository = AppointmentRepository()
    private val patientRepository: PatientRepository = PatientRepository()

    private lateinit var callback: (() -> Unit)

    private var appointment: Appointment? = null

    @FXML
    private lateinit var patientField: ComboBox<Patient>

    @FXML
    private lateinit var dateField: DatePicker

    @FXML
    private lateinit var transportField: CheckBox

    @FXML
    private lateinit var localityField: TextField

    @FXML
    private lateinit var periodField: ComboBox<AppointmentPeriod>

    @FXML
    private lateinit var localityHorizontalBox: HBox

    @FXML
    fun onClickTransportField() {
        localityHorizontalBox.isVisible = transportField.isSelected
        periodField.value = AppointmentPeriod.MORNING
    }

    @FXML
    fun onClickSaveButton() {
        val appointmentToDatabase = Appointment(
            id = appointment?.id,
            patient = patientField.value,
            date = dateField.value,
            locality = if (transportField.isSelected) localityField.text else null,
            period = if (transportField.isSelected) periodField.value else null
        )

        if (appointmentToDatabase.id != null) {
            appointmentRepository.update(appointmentToDatabase)
            informationMessage("Editar Consulta", "Consulta atualizada com sucesso!")
        } else {
            appointmentRepository.save(appointmentToDatabase)
            informationMessage("Editar Consulta", "Consulta inserida com sucesso!")
        }

        callback()
        patientField.scene.window.hide()
    }

    @FXML
    fun onClickCancelButton() {
        patientField.scene.window.hide()
    }

    override fun initialize(location: URL?, resources: ResourceBundle?) {
        patientField.items.addAll(patientRepository.findAll())
        periodField.items.addAll(AppointmentPeriod.entries.toTypedArray())
    }

    fun setCallback(callback: () -> Unit) {
        this.callback = callback
    }

    fun setAppointment(appointment: Appointment?) {
        this.appointment = appointment
        patientField.value = appointment?.patient?.value
        dateField.value = appointment?.date?.value
        localityField.text = appointment?.locality?.value
        periodField.value = appointment?.period?.value

        if (appointment?.locality?.value != null) {
            transportField.isSelected = true
            localityHorizontalBox.isVisible = true
        }
    }
}
