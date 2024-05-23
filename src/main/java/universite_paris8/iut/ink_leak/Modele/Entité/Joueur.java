package universite_paris8.iut.ink_leak.Modele.Entité;

public class Joueur extends Entité{

        private int taille_joueur;
        protected long invincibilite;
        protected long dernier_degat;

        public Joueur(String nom_joueur, int vie_joueur, int attaque_joueur, int taille_joueur, int vitesse_joueur) {
            super(nom_joueur, vie_joueur, attaque_joueur, vitesse_joueur);
            this.taille_joueur = taille_joueur;
            this.invincibilite = 1000;
            this.dernier_degat = -invincibilite;
            this.direction = 'S';
        }

        public int getTaille_joueur() {
            return taille_joueur;
        }

        public char getDirection() { return direction; }

        public void setDirection(char nv_direction) { this.direction = nv_direction; }

        public void prendre_degat(int degat) {
            if (this.vie_entiteProperty.getValue() - degat < 0) { this.vie_entiteProperty.setValue(0); }
            if (System.currentTimeMillis() - dernier_degat >= invincibilite) {
                this.vie_entiteProperty.setValue(this.vie_entiteProperty.getValue() - degat);
                this.dernier_degat = System.currentTimeMillis();
                System.out.println(this.vie_entiteProperty.getValue());
            }
        }

    }