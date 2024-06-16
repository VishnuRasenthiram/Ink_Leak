package universite_paris8.iut.ink_leak.Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class ControllerMenuDemarrage {

    @FXML
    private void debutJeu(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/universite_paris8/iut/ink_leak/terrain.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root,700,700));

            showDialog("AAAA",stage);

            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showDialog(String message, Stage stage) {
        Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.initOwner(stage);
        dialog.initStyle(StageStyle.UNDECORATED);

        VBox dialogVBox = new VBox();
        dialogVBox.setStyle("-fx-background-color: black; -fx-border-color: white; -fx-padding: 20;");
        Label label = new Label(message);
        label.setTextFill(Color.WHITE);
        label.setFont(new Font(16));
        label.setWrapText(true);

        dialogVBox.getChildren().add(label);
        Scene dialogScene = new Scene(dialogVBox, 400, 200);
        dialogScene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                dialog.close();
            }
        });

        dialog.setScene(dialogScene);
        dialog.show();
    }
}
