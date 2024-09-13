package com.app.prevention

import com.app.prevention.configuration.DatabaseConfiguration
import javafx.application.Application
import javafx.fxml.FXMLLoader
import javafx.scene.Scene
import javafx.stage.Stage

class MainApplication : Application() {
    override fun start(stage: Stage) {
        DatabaseConfiguration.initDatabase()

        val loader = FXMLLoader(javaClass.getResource("/com/app/prevention/main-view.fxml"))
        val scene = Scene(loader.load())

        stage.title = "Prevenção do Câncer de Mama"
        stage.scene = scene

        stage.show()
    }
}

fun main() {
    Application.launch(MainApplication::class.java)
}
