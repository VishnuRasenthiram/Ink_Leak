package universite_paris8.iut.ink_leak.Player;

public class Character {

        private String name;
        private int health;
        private int strength;

        private int size;

        public Character(String name, int health, int strength) {
            this.name = name;
            this.health = health;
            this.strength = strength;
            this.size = 20;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getHealth() {
            return health;
        }

        public void setHealth(int health) {
            this.health = health;
        }

        public int getStrength() {
            return strength;
        }

        public void setStrength(int strength) {
            this.strength = strength;
        }
    }