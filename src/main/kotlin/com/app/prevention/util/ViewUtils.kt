package com.app.prevention.util

import javafx.fxml.FXMLLoader
import javafx.scene.Scene
import javafx.stage.Stage

inline fun <reified T> T.loadView(path: String, title: String): Pair<Stage, FXMLLoader> {
    val loader = FXMLLoader(T::class.java.getResource(path))
    val scene = Scene(loader.load())
    val stage = Stage()

    stage.title = title
    stage.scene = scene

    return Pair(stage, loader)
}

fun Stage.showAndResize() {
    show()
    minHeight = height;
    minWidth = width;
}

fun Stage.showWaitAndResize() {
    showAndWait()
    minHeight = height;
    minWidth = width;
}