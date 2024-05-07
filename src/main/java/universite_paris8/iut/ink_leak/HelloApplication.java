package universite_paris8.iut.ink_leak;
import javafx.fxml.FXML;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import universite_paris8.iut.ink_leak.Player.Character;

import java.io.IOException;

public class HelloApplication extends Application {

    public static Character character;

    @Override
    public void start(Stage stage) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Pane root = fxmlLoader.load();

        character = new Character("LePlayer", 100, 50, 20, 50);

        Pane PlayerID = (Pane) root.lookup("#PlayerID");

        Circle circle = new Circle();
        circle.setCenterX(0);
        circle.setCenterY(0);
        circle.setRadius(character.getSize());
        circle.setId(character.getName());

        PlayerID.getChildren().add(circle);

        Scene scene = new Scene(root, 1020, 640);

        stage.setTitle("Les péripéties de Toad Vert");
        stage.setScene(scene);
        stage.show();


    }

    public static void main(String[] args) {
        launch();
    }
}