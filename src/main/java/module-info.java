module com.app.prevention {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.controlsfx.controls;
    requires exposed.core;
    requires exposed.java.time;
    requires org.xerial.sqlitejdbc;
    requires exposed.dao;
    requires kotlinx.coroutines.core;

    opens com.app.prevention to javafx.fxml;
    opens com.app.prevention.controller to javafx.fxml;

    exports com.app.prevention;
    exports com.app.prevention.controller;
    exports com.app.prevention.persistence.repository;
    exports com.app.prevention.persistence.dao;
    exports com.app.prevention.persistence.table;
}
