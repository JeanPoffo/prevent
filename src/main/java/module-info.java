module com.app.prevention {
    requires javafx.controls;
    requires javafx.fxml;
    requires kotlin.stdlib;

    requires org.controlsfx.controls;
    requires exposed.core;
    requires exposed.java.time;
    requires org.xerial.sqlitejdbc;

    opens com.app.prevention to javafx.fxml;
    opens com.app.prevention.controller to javafx.fxml;

    exports com.app.prevention;
    exports com.app.prevention.controller;
}