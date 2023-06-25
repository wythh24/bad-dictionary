module com.example.dictionarymaven {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires com.opencsv;

    opens com.Application.dictionarymaven to javafx.fxml;
    exports com.Application.dictionarymaven;
    exports com.Application.Controller;
    opens com.Application.Controller to javafx.fxml;

}