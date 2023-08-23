module com.example.CampusCarpool {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.logging;
    requires java.sql;

    exports com.example.CampusCarpool;
    opens com.example.CampusCarpool to javafx.fxml;

    exports com.example.CampusCarpool.graphiccontroller;
    opens com.example.CampusCarpool.graphiccontroller to javafx.fxml;

    exports com.example.CampusCarpool.model;
    opens com.example.CampusCarpool.model to javafx.fxml;

    exports com.example.CampusCarpool.exception;
    opens com.example.CampusCarpool.exception to javafx.fxml;

    exports com.example.CampusCarpool.bean;
    opens com.example.CampusCarpool.bean to javafx.fxml;

    exports com.example.CampusCarpool.engineering;
    opens com.example.CampusCarpool.engineering to javafx.fxml;

    exports com.example.CampusCarpool.appcontroller;
    opens com.example.CampusCarpool.appcontroller to javafx.fxml;

    exports com.example.CampusCarpool.dao;
}