package universite_paris8.iut.ink_leak.Player;

public class Character extends Entité{

        private int size;

        public Character(String name, int health, int strength, int size, int CharacterSpeed) {
            super(name, health, strength, CharacterSpeed );
            this.size = size;
        }


        public int getSize() {
            return size;
        }

    }