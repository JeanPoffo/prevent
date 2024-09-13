package com.app.prevention.controller

import javafx.fxml.FXML
import javafx.fxml.FXMLLoader
import javafx.scene.Scene
import javafx.stage.Stage

class MainController {

    @FXML
    fun onClickPatientButton() {
        val loader = FXMLLoader(javaClass.getResource("/com/app/prevention/patient-view.fxml"))
        val scene = Scene(loader.load())
        val stage = Stage()

        stage.title = "Pacientes"
        stage.scene = scene

        stage.show()

        stage.minHeight = stage.height;
        stage.minWidth = stage.width;
    }
}
