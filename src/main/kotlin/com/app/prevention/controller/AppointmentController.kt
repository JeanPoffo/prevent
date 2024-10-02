package com.app.prevention.controller

import com.app.prevention.model.Appointment
import com.app.prevention.model.AppointmentPeriod
import java.net.URL
import java.time.LocalDate
import java.util.ResourceBundle
import javafx.fxml.FXML
import javafx.fxml.Initializable
import javafx.scene.control.TableColumn
import javafx.scene.control.TableView

class AppointmentController: Initializable {

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
    fun onClickAddButton() {
        println("GO")
    }

    @FXML
    fun onClickEditButton() {
        println("GO")
    }

    @FXML
    fun onClickDeleteButton() {
        println("GO")
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
        appointmentTable.items.addAll(

        )
    }
}
