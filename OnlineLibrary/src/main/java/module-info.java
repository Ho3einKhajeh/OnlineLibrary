module org.example.onlinelibrary {
    requires javafx.controls;
    requires javafx.fxml;
    requires mysql.connector.java;
    requires java.sql;

    opens org.example.onlinelibrary to javafx.fxml;
    exports org.example.onlinelibrary;
}