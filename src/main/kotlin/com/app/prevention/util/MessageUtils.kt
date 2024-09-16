package com.app.prevention.util

import javafx.scene.control.Alert
import javafx.scene.control.ButtonType

fun questionMessage(title: String, message: String): Boolean {
    val alert = Alert(Alert.AlertType.CONFIRMATION)

    alert.title = title
    alert.headerText = message

    val result = alert.showAndWait()

    return result.get() == ButtonType.OK
}

fun informationMessage(title: String, message: String) {
    val alert = Alert(Alert.AlertType.INFORMATION)

    alert.title = title
    alert.headerText = message

    alert.showAndWait()
}