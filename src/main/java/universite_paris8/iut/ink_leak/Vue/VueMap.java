package universite_paris8.iut.ink_leak.Vue;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import universite_paris8.iut.ink_leak.Modele.Map;
import java.io.File;

public class VueMap {
    @FXML
    private TilePane tuileMap;
    public VueMap(TilePane tuileMap){
        this.tuileMap = tuileMap;
    }

    public void initMap(Map env){
        for (int i = 0; i < env.getMap().length; i++) {
            for (int j = 0; j < env.getMap()[i].length; j++) {
                creerTuile(env.getMap(i,j));
            }
        }
    }


    private void creerTuile(int tuile){

        Pane pane= new Pane();
        ImageView imageview= new ImageView();
        imageview.setFitHeight(32);
        imageview.setFitWidth(32);
        if(tuile==0){
            imageview.setImage(new Image(new File("src/main/resources/universite_paris8/iut/ink_leak/INK_LEAK_SPRITES/Background/floors/floor.png").toURI().toString()));
            pane.setId("sol");
        } else if (tuile==1) {
            imageview.setImage(new Image(new File("src/main/resources/universite_paris8/iut/ink_leak/INK_LEAK_SPRITES/Background/corridors/corridor_upward.png").toURI().toString()));
            pane.setId("rouge");
        } else if (tuile==2) {
            imageview.setImage(new Image(new File("src/main/resources/universite_paris8/iut/ink_leak/INK_LEAK_SPRITES/Background/floors/water.png").toURI().toString()));
            pane.setId("bleue");
        }


        pane.getChildren().add(imageview);


        tuileMap.getChildren().add(pane);



    }
}
