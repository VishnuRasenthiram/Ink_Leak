package universite_paris8.iut.ink_leak.Modele.Entité.Murs;

import universite_paris8.iut.ink_leak.Modele.Entité.Entité;
import universite_paris8.iut.ink_leak.Modele.Entité.Joueur.Joueur;
import universite_paris8.iut.ink_leak.Modele.Environnement;
import universite_paris8.iut.ink_leak.Modele.Generateurs.GenerateurEnnemis;
import universite_paris8.iut.ink_leak.Modele.Generateurs.GenerateurMurs;
import universite_paris8.iut.ink_leak.Modele.Generateurs.GenerateurObjets;
import universite_paris8.iut.ink_leak.Modele.Map;

import java.util.ArrayList;

public abstract class Mur extends Entité {

    public Mur(String nom_entite, int vie_entite, int attaque_entite, double largeur, double longueur, int vitesse_entite, long invincibilite, Environnement environnement) {
        super(nom_entite, vie_entite, attaque_entite, largeur, longueur, vitesse_entite, invincibilite,environnement );
    }

    public abstract ArrayList<Mur> déplacementMur(String déplacementDirection);
    //Verifie tous les mers qui sont cassé et les stock pour les supprimer une fois l'action effectué
    public ArrayList<Mur> verifMurCassé(){
        ArrayList<Mur> listeEnleve= new ArrayList<>();
        if(!super.getGenerateurMurs().getListeMurs().isEmpty()) {
            for (Mur mur : super.getGenerateurMurs().getListeMurs()) {
                if(mur.getVie()==0){
                    listeEnleve.add(mur);
                }
            }

        }
        return listeEnleve;
    }

    @Override
    public void attaque() {}
    @Override
    public void déplacement(String déplacementDirection) {}
    @Override
    public void gagner_vie(int nb_vie_gagnee) {}
}
