package universite_paris8.iut.ink_leak.Modele;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Comparator;
import java.util.List;
import java.util.ArrayList;

public class AEtoile {

    public static List<Integer> chercherChemin(int[][] grid, int startX, int startY, int endX, int endY) {
        int cols = grid[0].length;
        int rows = grid.length;

        PriorityQueue<Sommet> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(n -> n.cout));
        Map<String, Sommet> tousLesSommets = new HashMap<>();

        Sommet sommetInitial = new Sommet(startX, startY);
        sommetInitial.cout = 0;
        priorityQueue.add(sommetInitial);
        tousLesSommets.put(startX + "," + startY, sommetInitial);

        while (!priorityQueue.isEmpty()) {
            Sommet actuel = priorityQueue.poll();

            if (actuel.x == endX && actuel.y == endY) {
                return reconstituerTrajet(actuel);
            }

            for (Sommet voisin : obtenirVoisins(actuel, cols, rows, grid)) {
                String clef = voisin.x + "," + voisin.y;
                Sommet existant = tousLesSommets.get(clef);
                if (existant == null) {
                    tousLesSommets.put(clef, voisin);
                    existant = voisin;
                }

                int nouveauCout = actuel.cout + 1;
                if (nouveauCout < existant.cout) {
                    existant.precedent = actuel;
                    existant.direction = voisin.direction;
                    existant.cout = nouveauCout;
                    if (!priorityQueue.contains(existant)) {
                        priorityQueue.add(existant);
                    }
                }
            }
        }

        return new ArrayList<>();
    }

    private static List<Sommet> obtenirVoisins(Sommet sommet, int cols, int rows, int[][] grid) {
        List<Sommet> voisins = new ArrayList<>();
        int[][] directions = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};
        for (int[] direction : directions) {
            int newX = sommet.x + direction[0];
            int newY = sommet.y + direction[1];
            if (estValide(newX, newY, cols, rows, grid)) {
                voisins.add(new Sommet(newX, newY, calculerDirection(direction)));
            }
        }
        return voisins;
    }

    private static int calculerDirection(int[] direction) {
        if (direction[0] == 0 && direction[1] == -1) return 1;
        if (direction[0] == 0 && direction[1] == 1) return 2;
        if (direction[0] == -1 && direction[1] == 0) return 3;
        return 4;
    }

    private static boolean estValide(int x, int y, int cols, int rows, int[][] grid) {
        return (x >= 0 && x < cols && y >= 0 && y < rows && grid[y][x] == 0);
    }

    private static List<Integer> reconstituerTrajet(Sommet sommet) {
        List<Integer> chemin = new ArrayList<>();
        while (sommet.precedent != null) {
            chemin.add(0, sommet.direction);
            sommet = sommet.precedent;
        }
        return chemin;
    }

    static class Sommet {
        int x, y, cout, direction;
        Sommet precedent;

        public Sommet(int x, int y) {
            this(x, y, 0);
        }

        public Sommet(int x, int y, int direction) {
            this.x = x;
            this.y = y;
            this.cout = Integer.MAX_VALUE;
            this.direction = direction;
            this.precedent = null;
        }
    }
}
