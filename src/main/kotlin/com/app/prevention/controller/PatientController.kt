package com.app.prevention.controller

import com.app.prevention.dao.PatientDao
import com.app.prevention.dao.toPatientModel
import com.app.prevention.model.Patient
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
        patientTable.items.addAll(
            transaction {
                PatientDao.selectAll().map { it.toPatientModel() }
            }
        )
    }
}
