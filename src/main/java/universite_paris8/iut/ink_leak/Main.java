package universite_paris8.iut.ink_leak;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class Main extends Application {


    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("terrain.fxml"));
        Pane root = fxmlLoader.load();
        Scene scene = new Scene(root, 700, 700);
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        stage.setTitle("Ink Leak");
        stage.setScene(scene);
        stage.show();

        showDialog("Sortez de cet endroit...", stage);
    }

    public static void main(String[] args) {
        launch();
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