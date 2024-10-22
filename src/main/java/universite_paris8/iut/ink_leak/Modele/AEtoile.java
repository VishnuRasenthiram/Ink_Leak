package universite_paris8.iut.ink_leak.Modele;

import java.util.*;

public class AEtoile {
    public static List<Integer> chercherChemin(int[][] grid, int startX, int startY, int endX, int endY) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return new ArrayList<>();
        }

        int rows = grid.length;
        int cols = grid[0].length;

        // Validate input coordinates
        if (!estValide(startX, startY, cols, rows, grid) || !estValide(endX, endY, cols, rows, grid)) {
            return new ArrayList<>();
        }

        // Track visited nodes to prevent cycles
        boolean[][] visited = new boolean[rows][cols];

        PriorityQueue<Sommet> openSet = new PriorityQueue<>(Comparator.comparingInt(n -> n.f));
        Set<String> openSetKeys = new HashSet<>(); // Track nodes in openSet for efficient contains() checks

        Sommet start = new Sommet(startX, startY);
        start.g = 0;
        start.f = heuristic(startX, startY, endX, endY);

        openSet.add(start);
        openSetKeys.add(startX + "," + startY);

        while (!openSet.isEmpty()) {
            Sommet current = openSet.poll();
            openSetKeys.remove(current.x + "," + current.y);

            if (current.x == endX && current.y == endY) {
                return reconstituerTrajet(current);
            }

            visited[current.y][current.x] = true;

            for (int[] dir : new int[][]{{0, -1}, {0, 1}, {-1, 0}, {1, 0}}) {
                int newX = current.x + dir[0];
                int newY = current.y + dir[1];

                if (!estValide(newX, newY, cols, rows, grid) || visited[newY][newX]) {
                    continue;
                }

                int direction = calculerDirection(dir);
                int newG = current.g + 1;

                Sommet neighbor = new Sommet(newX, newY, direction);
                String neighborKey = newX + "," + newY;

                if (!openSetKeys.contains(neighborKey)) {
                    neighbor.g = newG;
                    neighbor.f = newG + heuristic(newX, newY, endX, endY);
                    neighbor.precedent = current;
                    openSet.add(neighbor);
                    openSetKeys.add(neighborKey);
                }
            }

            // Safety check to prevent infinite loops
            if (openSet.size() > rows * cols) {
                return new ArrayList<>();
            }
        }

        return new ArrayList<>();
    }

    private static int heuristic(int x1, int y1, int x2, int y2) {
        return Math.abs(x1 - x2) + Math.abs(y1 - y2); // Manhattan distance
    }

    private static boolean estValide(int x, int y, int cols, int rows, int[][] grid) {
        return x >= 0 && x < cols && y >= 0 && y < rows && (grid[y][x] == 0 || grid[y][x] == 1);
    }

    private static int calculerDirection(int[] direction) {
        if (direction[0] == 0 && direction[1] == -1) return 1;      // Up
        if (direction[0] == 0 && direction[1] == 1) return 2;       // Down
        if (direction[0] == -1 && direction[1] == 0) return 3;      // Left
        return 4;                                                   // Right
    }

    private static List<Integer> reconstituerTrajet(Sommet sommet) {
        List<Integer> chemin = new ArrayList<>();
        Sommet current = sommet;
        while (current.precedent != null) {
            chemin.add(0, current.direction);
            current = current.precedent;
        }
        return chemin;
    }

    static class Sommet {
        int x, y, f, g, direction;
        Sommet precedent;

        public Sommet(int x, int y) {
            this(x, y, 0);
        }

        public Sommet(int x, int y, int direction) {
            this.x = x;
            this.y = y;
            this.f = Integer.MAX_VALUE;
            this.g = Integer.MAX_VALUE;
            this.direction = direction;
            this.precedent = null;
        }
    }
}