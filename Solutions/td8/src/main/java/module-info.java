module com.td8 {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.td8 to javafx.fxml;
    exports com.td8;
}
