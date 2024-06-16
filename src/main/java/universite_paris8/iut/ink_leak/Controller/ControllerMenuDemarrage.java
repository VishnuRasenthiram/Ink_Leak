package universite_paris8.iut.ink_leak.Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;

public class ControllerMenuDemarrage {

    @FXML
    private void debutJeu(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/universite_paris8/iut/ink_leak/terrain.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root,700,700));

            showCommande(stage);

            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showCommande(Stage stage) {
        Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.initOwner(stage);
        dialog.initStyle(StageStyle.TRANSPARENT);

        ImageView imageView = new ImageView();
        imageView.setStyle("-fx-background-color: transparent;");
        imageView.setId("imageView");
        imageView.setFitHeight(400);
        imageView.setFitWidth(400);
        imageView.setImage(new Image(new File("src/main/resources/universite_paris8/iut/ink_leak/INK_LEAK_SPRITES/UI/commandes.png").toURI().toString()));

        VBox dialogVBox = new VBox();
        dialogVBox.setStyle("-fx-background-color: transparent;");

        dialogVBox.getChildren().add(imageView);

        Scene dialogScene = new Scene(dialogVBox, 400, 400);
        dialogScene.setFill(null);

        dialogScene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                dialog.close();
            }
        });

        dialog.setScene(dialogScene);
        dialog.show();
    }

    private void showPouvoirs(Stage stage) {
        Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.initOwner(stage);
        dialog.initStyle(StageStyle.TRANSPARENT);

        ImageView imageView = new ImageView();
        imageView.setStyle("-fx-background-color: transparent;");
        imageView.setId("imageView");
        imageView.setFitHeight(400);
        imageView.setFitWidth(400);
        imageView.setImage(new Image(new File("src/main/resources/universite_paris8/iut/ink_leak/INK_LEAK_SPRITES/UI/commandes.png").toURI().toString()));

        VBox dialogVBox = new VBox();
        dialogVBox.setStyle("-fx-background-color: transparent;");

        dialogVBox.getChildren().add(imageView);

        Scene dialogScene = new Scene(dialogVBox, 400, 400);
        dialogScene.setFill(null);
        dialogScene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                dialog.close();
            }
        });

        dialog.setScene(dialogScene);
        dialog.show();
    }

}
