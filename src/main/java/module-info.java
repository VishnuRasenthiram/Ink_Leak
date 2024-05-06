module universite_paris8.iut.ink_leak {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;

    opens universite_paris8.iut.ink_leak to javafx.fxml;
    exports universite_paris8.iut.ink_leak;
}