module universite_paris8.iut.ink_leak {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires java.sql;
    requires java.desktop;
    requires junit;

    opens universite_paris8.iut.ink_leak to javafx.fxml;
    exports universite_paris8.iut.ink_leak;
    exports universite_paris8.iut.ink_leak.Controller;
    opens universite_paris8.iut.ink_leak.Controller to javafx.fxml;
    exports universite_paris8.iut.ink_leak.Vue;
    opens universite_paris8.iut.ink_leak.Vue to javafx.fxml;
    exports universite_paris8.iut.ink_leak.Controller.ListeObservable;
    opens universite_paris8.iut.ink_leak.Controller.ListeObservable to javafx.fxml;
    exports universite_paris8.iut.ink_leak.Controller.Observable;
    opens universite_paris8.iut.ink_leak.Controller.Observable to javafx.fxml;
    exports universite_paris8.iut.ink_leak.Modele.TestJunit to junit;
}