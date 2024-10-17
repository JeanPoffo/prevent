package com.app.prevention.controller

import com.app.prevention.util.loadView
import com.app.prevention.util.showAndResize
import javafx.fxml.FXML

class MainController {

    @FXML
    fun onClickPatientButton() {
        val (stage) = loadView("/com/app/prevention/patient-view.fxml", "Pacientes")
        stage.showAndResize()
    }

    @FXML
    fun onClickBoardingButton() {
        val (stage) = loadView("/com/app/prevention/appointment-view.fxml", "Consultas")
        stage.showAndResize()
    }
}
