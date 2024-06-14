package universite_paris8.iut.ink_leak.Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class LaunchController {
    @FXML
    private BorderPane borderPaneDebut;

    @FXML
    private void startGame(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/universite_paris8/iut/ink_leak/terrain.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root,700,700));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
