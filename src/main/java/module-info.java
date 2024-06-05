module universite_paris8.iut.ink_leak {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires java.sql;
    requires java.desktop;

    opens universite_paris8.iut.ink_leak to javafx.fxml;
    exports universite_paris8.iut.ink_leak;
    exports universite_paris8.iut.ink_leak.Controller;
    opens universite_paris8.iut.ink_leak.Controller to javafx.fxml;
    exports universite_paris8.iut.ink_leak.Vue;
    opens universite_paris8.iut.ink_leak.Vue to javafx.fxml;
}