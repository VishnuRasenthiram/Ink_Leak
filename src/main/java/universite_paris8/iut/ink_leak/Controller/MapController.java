package universite_paris8.iut.ink_leak.Controller;
import javafx.fxml.FXML;

import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.shape.Circle;
import universite_paris8.iut.ink_leak.Modele.Map;
import javafx.scene.Node;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import java.net.URL;
import java.util.ResourceBundle;




public class MapController implements Initializable {
    private Map env;
    @FXML
    private Label welcomeText;


    @FXML
    private TilePane tuileMap;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavsaFX Application!");
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        this.env= new Map();


        for (int i = 0; i < env.getMap().length; i++) {
            for (int j = 0; j < env.getMap()[i].length; j++) {
                creerTuile(env.getMap(i,j));
            }
        }

    }

    private void creerTuile(int tuile) {
        //System.out.println("ajouter sprite");
        Pane pane;

        if(tuile==1){
            pane= new Pane();
            Rectangle r =new Rectangle(32,32);
            r.setFill(Color.RED);
            pane.getChildren().add(r);
        }
        else if (tuile==2){
            pane= new Pane();
            Rectangle r =new Rectangle(32,32);
            r.setFill(Color.BLUE);
            pane.getChildren().add(r);
        }
        else{
            pane= new Pane();
            Rectangle r =new Rectangle(32,32);
            r.setFill(Color.GRAY);
            pane.getChildren().add(r);
        }

        tuileMap.getChildren().add(pane);
        // ils ont le meme identifiant


    }
}