package universite_paris8.iut.ink_leak.Modele.Entité;

public class Joueur extends Entité{

        private int size;
        protected long invincibilite;
        protected long dernier_degat;

        public Joueur(String name, int health, int strength, int size, int CharacterSpeed) {
            super(name, health, strength, CharacterSpeed);
            this.size = size;
            this.invincibilite = 1000;
            this.dernier_degat = -invincibilite;
        }

        public int getSize() {
            return size;
        }

        public void prendre_degat(int degat) {
            if (System.currentTimeMillis() - dernier_degat >= invincibilite) {
                this.health.setValue(this.health.getValue() - degat);
                this.dernier_degat = System.currentTimeMillis();
        }
    }

    }