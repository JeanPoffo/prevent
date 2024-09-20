package com.app.prevention.controller

import com.app.prevention.dao.ScheduleDao
import com.app.prevention.dao.toScheduleModel
import com.app.prevention.model.Schedule
import java.net.URL
import java.time.LocalDateTime
import java.util.ResourceBundle
import javafx.fxml.FXML
import javafx.fxml.Initializable
import javafx.scene.control.TableColumn
import javafx.scene.control.TableView
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction

class ScheduleController: Initializable {

    @FXML
    lateinit var scheduleTable: TableView<Schedule>

    @FXML
    lateinit var transporterColumn: TableColumn<Schedule, String>

    @FXML
    lateinit var dateTimeColumn: TableColumn<Schedule, LocalDateTime>

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
        transporterColumn.setCellValueFactory { it.value.transporter }
        dateTimeColumn.setCellValueFactory { it.value.dateTime }
    }

    private fun refreshTableData() {
        scheduleTable.items.clear()
        scheduleTable.items.addAll(
            transaction { ScheduleDao.selectAll().map { it.toScheduleModel() } }
        )
    }
}
