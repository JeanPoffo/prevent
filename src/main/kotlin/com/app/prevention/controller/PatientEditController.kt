package com.app.prevention.controller

import com.app.prevention.dao.PatientDao
import com.app.prevention.dao.insert
import com.app.prevention.dao.update
import com.app.prevention.model.Patient
import com.app.prevention.util.informationMessage
import javafx.fxml.FXML
import javafx.scene.control.DatePicker
import javafx.scene.control.TextField

class PatientEditController {

    @FXML
    private lateinit var cpfField: TextField

    @FXML
    private lateinit var nameField: TextField

    @FXML
    private lateinit var birthDateField: DatePicker

    @FXML
    private lateinit var addressField: TextField

    @FXML
    private lateinit var neighborhoodField: TextField

    @FXML
    private lateinit var cityField: TextField

    @FXML
    private lateinit var susNumberField: TextField

    @FXML
    fun onClickSaveButton() {
        runCatching {
            this.patient = Patient(
                id = this.patient?.id,
                cpf = cpfField.text,
                name = nameField.text,
                birthDate = birthDateField.value,
                address = addressField.text,
                neighborhood = neighborhoodField.text,
                city = cityField.text,
                susNumber = susNumberField.text
            )
        }.onSuccess {
            if (this.patient?.id != null) {
                PatientDao.update(this.patient!!)
                informationMessage("Editar Paciente", "Paciente atualizado com sucesso!")
            } else {
                PatientDao.insert(this.patient!!)
                informationMessage("Editar Paciente", "Paciente inserido com sucesso!")
            }

            callback()

            cpfField.scene.window.hide()
        }.onFailure {
            informationMessage("Editar Paciente", "Preencha os dados corretamente!")
        }
    }

    @FXML
    fun onClickCancelButton() {
        cpfField.scene.window.hide()
    }

    private lateinit var callback: (() -> Unit)

    private var patient: Patient? = null

    fun setCallback(callback: () -> Unit) {
        this.callback = callback
    }

    fun setPatient(patient: Patient?) {
        this.patient = patient
        refreshFields()
    }

    private fun refreshFields() {
        cpfField.text = patient?.cpf?.value
        nameField.text = patient?.name?.value
        birthDateField.value = patient?.birthDate?.value
        addressField.text = patient?.address?.value
        neighborhoodField.text = patient?.neighborhood?.value
        cityField.text = patient?.city?.value
        susNumberField.text = patient?.susNumber?.value
    }
}
