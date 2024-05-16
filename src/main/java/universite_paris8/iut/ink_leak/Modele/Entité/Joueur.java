package universite_paris8.iut.ink_leak.Modele.Entité;

public class Joueur extends Entité{

        private int size;

        public Joueur(String name, int health, int strength, int size, int CharacterSpeed) {
            super(name, health, strength, CharacterSpeed);
            this.size = size;
        }


        public int getSize() {
            return size;
        }

    }