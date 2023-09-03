module com.example.CampusCarpool {
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

    exports com.example.campuscarpool.engineering;
    opens com.example.campuscarpool.engineering to javafx.fxml;

    exports com.example.campuscarpool.appcontroller;
    opens com.example.campuscarpool.appcontroller to javafx.fxml;

    exports com.example.campuscarpool.dao;
}