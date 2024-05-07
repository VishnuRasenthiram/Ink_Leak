package universite_paris8.iut.ink_leak;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import universite_paris8.iut.ink_leak.Player.Character;

import java.net.URL;
import java.util.ResourceBundle;

public class  HelloController implements Initializable {
    public static Character character;
    @FXML
    public  BorderPane toutenhaut;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        character = new Character("LePlayer", 100, 50, 20, 50);

        Pane PlayerID = (Pane) toutenhaut.lookup("#PlayerID");

        Circle circle = new Circle();
        circle.setCenterX(0);
        circle.setCenterY(0);
        circle.setRadius(character.getSize());
        circle.setId(character.getName());

        PlayerID.getChildren().add(circle);
    }
    @FXML
    private Pane PlayerID;

    public static int getCharacterSpeed() {
        return character.getCharacterSpeed();
    }
    private int PlayerSpeed;
    @FXML
    protected void moove() {

        Circle circle = (Circle) PlayerID.lookup("#LePlayer");
        PlayerSpeed = getCharacterSpeed();

        PlayerID.setOnKeyPressed(e -> {

            if (e.getCode() == KeyCode.UP) {
                circle.setTranslateY(circle.getTranslateY() - PlayerSpeed);
            } else if (e.getCode() == KeyCode.DOWN) {
                circle.setTranslateY(circle.getTranslateY() + PlayerSpeed);

            } else if (e.getCode() == KeyCode.LEFT) {
                circle.setTranslateX(circle.getTranslateX() - PlayerSpeed);

            } else if (e.getCode() == KeyCode.RIGHT) {
                circle.setTranslateX(circle.getTranslateX() + PlayerSpeed);

            }
        });
    }


}