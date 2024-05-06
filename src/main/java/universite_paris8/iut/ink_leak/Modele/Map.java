package universite_paris8.iut.Ink_Leak.Modele.Modele;

public class Map {

    private int longueur;
    private int largeur;

    public Map(int[][] tab){
        this.largeur= tab.length;
        this.longueur=tab[0].length;
    }

    public int getLongueur() {
        return longueur;
    }

    public void setLongueur(int longueur) {
        this.longueur = longueur;
    }

    public int getLargeur() {
        return largeur;
    }

    public void setLargeur(int largeur) {
        this.largeur = largeur;
    }
}
