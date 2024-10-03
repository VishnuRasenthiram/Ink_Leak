package universite_paris8.iut.ink_leak.Modele.Entité.Objets.ObjetRecuperable;
import universite_paris8.iut.ink_leak.Modele.Entité.Objets.Objets;
import universite_paris8.iut.ink_leak.Modele.Entité.Pouvoirs.Pouvoirs;
import universite_paris8.iut.ink_leak.Modele.Environnement;


public abstract class ObjetRecuperable extends Objets {
    private Pouvoirs pouvoirs;
    public ObjetRecuperable(String idPouvoir, Environnement environnement, Pouvoirs pouvoirs) {
        super(idPouvoir,environnement);
        this.pouvoirs = pouvoirs;

    }

    @Override
    public void action() {
        if(super.getJoueur().enContact(this) && super.getNbFoisRecuperable()>0){
            super.setNbFoisRecuperable(super.getNbFoisRecuperable()-1);
            super.getJoueur().ajoutPouvoir(pouvoirs);
        }
    }
}
