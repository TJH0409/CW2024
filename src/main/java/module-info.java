module com.example.demo {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.demo.actor to javafx.fxml;
    opens com.example.demo.levels to javafx.fxml;
    opens com.example.demo.image to javafx.fxml;
    opens com.example.demo.projectile to javafx.fxml;
    exports com.example.demo.controller;
}