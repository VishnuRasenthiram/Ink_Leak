package universite_paris8.iut.ink_leak;
import javafx.fxml.FXML;

import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;


public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    private Pane PlayerID;


    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavsaFX Application!");
    }


    @FXML
    protected void moove() {
        Circle circle = (Circle) PlayerID.lookup("#LePlayer");

        PlayerID.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.UP) {
                circle.setCenterY(circle.getCenterY() - 10);
            } else if (e.getCode() == KeyCode.DOWN) {
                circle.setCenterY(circle.getCenterY() + 10);
            } else if (e.getCode() == KeyCode.LEFT) {
                circle.setCenterX(circle.getCenterX() - 10);
            } else if (e.getCode() == KeyCode.RIGHT) {
                circle.setCenterX(circle.getCenterX() + 10);
            }
        });
    }
}