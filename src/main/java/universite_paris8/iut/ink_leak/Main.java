package universite_paris8.iut.ink_leak;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import universite_paris8.iut.ink_leak.Player.Character;

import java.io.IOException;

public class Main extends Application {

    public static Pane root;

    @Override
    public void start(Stage stage) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("hello-view.fxml"));
        root = fxmlLoader.load();
        Scene scene = new Scene(root, 1020, 640);
        stage.setTitle("Les péripéties de Toad Vert");
        stage.setScene(scene);
        stage.show();


    }

    public static Pane getRoot() {
        return root;
    }

    public static void main(String[] args) {
        launch();
    }
}
