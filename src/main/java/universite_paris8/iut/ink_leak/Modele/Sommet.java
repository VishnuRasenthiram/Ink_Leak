package universite_paris8.iut.ink_leak.Modele;

import java.util.ArrayList;
import java.util.List;

// Classe représentant un sommet dans le graphe
public class Sommet {
    private Position position;
    private boolean traversable;
    private List<Sommet> voisins;

    public Sommet(Position position, boolean traversable) {
        this.position = position;
        this.traversable = traversable;
        this.voisins = new ArrayList<>();
    }

    public Position getPosition() {
        return position;
    }

    public boolean isTraversable() {
        return traversable;
    }

    public void setTraversable(boolean traversable) {
        this.traversable = traversable;
    }

    public List<Sommet> getVoisins() {
        return voisins;
    }

    public void addVoisin(Sommet voisin) {
        this.voisins.add(voisin);
    }

    // Méthode pour calculer la distance entre deux sommets
    public double distance(Sommet autre) {
        return Math.sqrt(Math.pow(this.position.getX() - autre.position.getX(), 2) +
                Math.pow(this.position.getY() - autre.position.getY(), 2));
    }
}
