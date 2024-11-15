package universite_paris8.iut.ink_leak.Modele;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public class Position {

    private DoubleProperty posXProperty;
    private DoubleProperty posYProperty;

    public Position() {
        this.posXProperty = new SimpleDoubleProperty();
        this.posYProperty = new SimpleDoubleProperty();
    }

    public double getPosY() {
        return posYProperty.getValue();
    }

    public DoubleProperty posYPropertyProperty() {
        return posYProperty;
    }

    public void setPosY(double posY) {
        this.posYProperty.setValue(posY);
    }

    public double getPosX() {
        return posXProperty.getValue();
    }

    public DoubleProperty posXPropertyProperty() {
        return posXProperty;
    }

    public void setPosX(double posX) {
        this.posXProperty.setValue(posX);
    }
}
