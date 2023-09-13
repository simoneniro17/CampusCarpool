module com.example.campuscarpool {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.logging;
    requires java.sql;

    exports com.example.campuscarpool;
    opens com.example.campuscarpool to javafx.fxml;

    exports com.example.campuscarpool.graphiccontroller;
    opens com.example.campuscarpool.graphiccontroller to javafx.fxml;

    exports com.example.campuscarpool.model;
    opens com.example.campuscarpool.model to javafx.fxml;

    exports com.example.campuscarpool.exception;
    opens com.example.campuscarpool.exception to javafx.fxml;

    exports com.example.campuscarpool.bean;
    opens com.example.campuscarpool.bean to javafx.fxml;

    exports com.example.campuscarpool.engineering.factory;
    opens com.example.campuscarpool.engineering.factory to javafx.fxml;

    exports com.example.campuscarpool.engineering.observer;
    opens com.example.campuscarpool.engineering.observer to javafx.fxml;

    exports com.example.campuscarpool.appcontroller;
    opens com.example.campuscarpool.appcontroller to javafx.fxml;

    exports com.example.campuscarpool.connection;
    opens com.example.campuscarpool.connection to javafx.fxml;

    exports com.example.campuscarpool.dao;
    exports com.example.campuscarpool.dao.queries;

}