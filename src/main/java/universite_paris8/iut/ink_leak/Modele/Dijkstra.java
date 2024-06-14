package universite_paris8.iut.ink_leak.Modele;

import java.util.*;

public class Dijkstra {

    public static List<Integer> dijkstra(int[][] mapData, int startX, int startY, int targetX, int targetY) {
        int longueur = mapData[0].length;
        int hauteur = mapData.length;

        PriorityQueue<Node> queue = new PriorityQueue<>(Comparator.comparingInt(node -> node.distance));
        java.util.Map<String, Node> allNodes = new HashMap<>();

        Node NoeufDeDepart = new Node(startX, startY);
        NoeufDeDepart.distance = 0;
        queue.add(NoeufDeDepart);
        allNodes.put(startX + "," + startY, NoeufDeDepart);

        while (!queue.isEmpty()) {
            Node currentNode = queue.poll();

            if (currentNode.x == targetX && currentNode.y == targetY) {
                return reconstructPath(currentNode);
            }

            for (int dir = 1; dir <= 4; dir++) {
                int voisinX = currentNode.x;
                int voisinY = currentNode.y;

                switch (dir) {
                    case 1: // en haut
                        voisinY--;
                        break;
                    case 2: // en bas
                        voisinY++;
                        break;
                    case 3: // haut
                        voisinX--;
                        break;
                    case 4: // RIGHT
                        voisinX++;
                        break;
                }

                if (estValideCell(voisinX, voisinY, longueur, hauteur, mapData)) {
                    String voisinKey = voisinX + "," + voisinY;
                    Node voisinNode = allNodes.getOrDefault(voisinKey, new Node(voisinX, voisinY));
                    allNodes.putIfAbsent(voisinKey, voisinNode);

                    int newDistance = currentNode.distance + 1;

                    if (newDistance < voisinNode.distance) {
                        voisinNode.distance = newDistance;
                        voisinNode.previous = currentNode;
                        voisinNode.direction = dir;
                        queue.add(voisinNode);
                    }
                }
            }
        }

        System.out.println("No path found.");
        return null;
    }

    private static boolean estValideCell(int x, int y, int longueur, int hauteur, int[][] map) {
        boolean estValide = x >= 0 && x < longueur && y >= 0 && y < hauteur && map[y][x] == 0;
        return estValide;
    }

    private static List<Integer> reconstructPath(Node node) {
        List<Integer> path = new ArrayList<>();

        while (node.previous != null) {
            path.add(0, node.direction);
            node = node.previous;
        }

        return path;
    }
}

class Node {
    int x;
    int y;
    int distance;
    Node previous;
    int direction;

    public Node(int x, int y) {
        this.x = x;
        this.y = y;
        this.distance = Integer.MAX_VALUE;
        this.previous = null;
        this.direction = 0;
    }
}