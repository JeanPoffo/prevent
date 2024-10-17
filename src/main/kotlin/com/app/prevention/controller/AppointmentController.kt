package com.app.prevention.controller

import com.app.prevention.model.Appointment
import com.app.prevention.model.AppointmentPeriod
import com.app.prevention.persistence.repository.AppointmentRepository
import com.app.prevention.util.informationMessage
import com.app.prevention.util.loadView
import com.app.prevention.util.showAndResize
import java.net.URL
import java.time.LocalDate
import java.util.ResourceBundle
import javafx.fxml.FXML
import javafx.fxml.Initializable
import javafx.scene.control.TableColumn
import javafx.scene.control.TableView

class AppointmentController: Initializable {

    private val repository: AppointmentRepository = AppointmentRepository()

    @FXML
    lateinit var appointmentTable: TableView<Appointment>

    @FXML
    lateinit var cpfColumn: TableColumn<Appointment, String>

    @FXML
    lateinit var nameColumn: TableColumn<Appointment, String>

    @FXML
    lateinit var dateColumn: TableColumn<Appointment, LocalDate>

    @FXML
    lateinit var localityColumn: TableColumn<Appointment, String>

    @FXML
    lateinit var periodColumn: TableColumn<Appointment, AppointmentPeriod>

    @FXML
    fun onClickAddButton() { loadAppointmentEditView() }

    @FXML
    fun onClickEditButton() {
        val appointment = appointmentTable.selectionModel.selectedItem

        if (appointment != null) {
            loadAppointmentEditView(appointment)
        } else {
            informationMessage("Editar Consulta", "Selecione uma consulta para editar.")
        }
    }

    @FXML
    fun onClickDeleteButton() {
        val appointment = appointmentTable.selectionModel.selectedItem

        if (appointment != null) {
            repository.delete(appointment)

            refreshTableData()

            informationMessage("Excluir Consulta", "Consulta excluída com sucesso!")
        } else {
            informationMessage("Excluir Consulta", "Selecione uma consulta para excluir.")
        }
    }

    override fun initialize(location: URL?, resources: ResourceBundle?) {
        initializeTable()
        refreshTableData()
    }

    private fun initializeTable() {
        cpfColumn.setCellValueFactory { it.value.patient.get().cpf }
        nameColumn.setCellValueFactory { it.value.patient.get().name }
        dateColumn.setCellValueFactory { it.value.date }
        localityColumn.setCellValueFactory { it.value.locality }
        periodColumn.setCellValueFactory { it.value.period }
    }

    private fun refreshTableData() {
        appointmentTable.items.clear()
        appointmentTable.items.addAll(repository.findAll())
    }

    private fun loadAppointmentEditView(appointment: Appointment? = null) {
        val (stage, loader) = loadView("/com/app/prevention/appointment-edit-view.fxml", "Adicionar")

        val controller = loader.getController<AppointmentEditController>()
        controller.setAppointment(appointment)
        controller.setCallback { refreshTableData() }

        stage.showAndResize()
    }
}
