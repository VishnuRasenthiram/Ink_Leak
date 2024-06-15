package universite_paris8.iut.ink_leak.Modele;

import java.util.*;

public class Dijkstra {

    public static List<Integer> dijkstraAstar(int[][] mapData, int startX, int startY, int targetX, int targetY) {
        int longueur = mapData[0].length;
        int hauteur = mapData.length;

        PriorityQueue<Node> queue = new PriorityQueue<>(Comparator.comparingInt(node -> node.fScore));
        java.util.Map<String, Node> allNodes = new HashMap<>();

        Node startNode = new Node(startX, startY);
        startNode.gScore = 0;
        startNode.hScore = heuristic(startNode, targetX, targetY);
        startNode.fScore = startNode.gScore + startNode.hScore;
        queue.add(startNode);
        allNodes.put(startX + "," + startY, startNode);

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
                    case 3: // gauche
                        voisinX--;
                        break;
                    case 4: // droite
                        voisinX++;
                        break;
                }

                if (estValideCell(voisinX, voisinY, longueur, hauteur, mapData)) {
                    String voisinKey = voisinX + "," + voisinY;
                    Node voisinNode = allNodes.getOrDefault(voisinKey, new Node(voisinX, voisinY));
                    allNodes.putIfAbsent(voisinKey, voisinNode);

                    int tentativeGScore = currentNode.gScore + 1;

                    if (tentativeGScore < voisinNode.gScore) {
                        voisinNode.previous = currentNode;
                        voisinNode.direction = dir;
                        voisinNode.gScore = tentativeGScore;
                        voisinNode.hScore = heuristic(voisinNode, targetX, targetY);
                        voisinNode.fScore = voisinNode.gScore + voisinNode.hScore;

                        if (!queue.contains(voisinNode)) {
                            queue.add(voisinNode);
                        }
                    }
                }
            }
        }

        return null;
    }

    private static boolean estValideCell(int x, int y, int longueur, int hauteur, int[][] map) {
        return x >= 0 && x < longueur && y >= 0 && y < hauteur && map[y][x] == 0;
    }

    private static List<Integer> reconstructPath(Node node) {
        List<Integer> path = new ArrayList<>();

        while (node.previous != null) {
            path.add(0, node.direction);
            node = node.previous;
        }

        return path;
    }

    private static int heuristic(Node node, int targetX, int targetY) {
        return (int) Math.sqrt(Math.pow(node.x - targetX, 2) + Math.pow(node.y - targetY, 2));
    }

    static class Node {
        int x;
        int y;
        int gScore; // Distance from start
        int hScore; // Heuristic estimate of distance to target
        int fScore; // gScore + hScore
        Node previous;
        int direction;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
            this.gScore = Integer.MAX_VALUE;
            this.hScore = 0;
            this.fScore = Integer.MAX_VALUE;
            this.previous = null;
            this.direction = 0;
        }
    }


}
