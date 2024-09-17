package com.app.prevention.controller

import com.app.prevention.dao.PatientDao
import com.app.prevention.dao.delete
import com.app.prevention.dao.toPatientModel
import com.app.prevention.model.Patient
import com.app.prevention.util.informationMessage
import com.app.prevention.util.loadView
import com.app.prevention.util.questionMessage
import com.app.prevention.util.showAndResize
import com.app.prevention.util.showWaitAndResize
import java.net.URL
import java.util.ResourceBundle
import javafx.fxml.FXML
import javafx.fxml.Initializable
import javafx.scene.control.TableColumn
import javafx.scene.control.TableView
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction

class PatientController: Initializable {

    @FXML
    private lateinit var patientTable: TableView<Patient>

    @FXML
    private lateinit var cpfColumn: TableColumn<Patient, String>

    @FXML
    private lateinit var nameColumn: TableColumn<Patient, String>

    @FXML
    private fun onClickAddButton() { loadPatientEditView() }

    @FXML
    private fun onClickEditButton() {
        val patient = patientTable.selectionModel.selectedItem

        if (patient != null) {
            loadPatientEditView(patient)
        } else {
            informationMessage("Editar Paciente", "Selecione um paciente para editar.")
        }
    }

    @FXML
    private fun onClickDeleteButton() {
        val patient = patientTable.selectionModel.selectedItem

        if (patient != null) {
            if (questionMessage("Excluir Paciente", "Deseja realmente excluir o paciente selecionado?")) {
                transaction { PatientDao.delete(patient) }
                refreshTableData()

                informationMessage("Excluir Paciente", "Paciente excluído com sucesso!")
            }
        } else {
            informationMessage("Excluir Paciente", "Selecione um paciente para excluir.")
        }
    }

    override fun initialize(location: URL?, resources: ResourceBundle?) {
        initializeTable()
        refreshTableData()
    }

    private fun initializeTable() {
        cpfColumn.setCellValueFactory { it.value.cpf }
        nameColumn.setCellValueFactory { it.value.name }
    }

    private fun refreshTableData() {
        patientTable.items.clear()
        patientTable.items.addAll(transaction { PatientDao.selectAll().map { it.toPatientModel() } })
    }

    private fun loadPatientEditView(patient: Patient? = null) {
        val (stage, loader) = loadView("/com/app/prevention/patient-edit-view.fxml", "Adicionar")

        val controller = loader.getController<PatientEditController>()
        controller.setPatient(patient)
        controller.setCallback { refreshTableData() }

        stage.showWaitAndResize()
    }
}
