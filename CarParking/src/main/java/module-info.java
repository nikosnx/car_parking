module com.mycompany.learnproject {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    //requires com.gluonhq.charm.glisten;

    opens com.mycompany.parking to javafx.fxml;
    exports com.mycompany.parking;
}
