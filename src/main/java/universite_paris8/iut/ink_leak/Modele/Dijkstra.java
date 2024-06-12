package universite_paris8.iut.ink_leak.Modele;

import java.util.*;

public class Dijkstra {

    public static List<Integer> dijkstra(int[][] mapData, int startX, int startY, int targetX, int targetY) {
        int width = mapData[0].length;
        int height = mapData.length;

        PriorityQueue<Node> queue = new PriorityQueue<>(Comparator.comparingInt(node -> node.distance));
        java.util.Map<String, Node> allNodes = new HashMap<>();

        Node startNode = new Node(startX, startY);
        startNode.distance = 0;
        queue.add(startNode);
        allNodes.put(startX + "," + startY, startNode);

        while (!queue.isEmpty()) {
            Node currentNode = queue.poll();

            if (currentNode.x == targetX && currentNode.y == targetY) {
                return reconstructPath(currentNode);
            }

            for (int dir = 1; dir <= 4; dir++) {
                int neighborX = currentNode.x;
                int neighborY = currentNode.y;

                switch (dir) {
                    case 1: // UP
                        neighborY--;
                        break;
                    case 2: // DOWN
                        neighborY++;
                        break;
                    case 3: // LEFT
                        neighborX--;
                        break;
                    case 4: // RIGHT
                        neighborX++;
                        break;
                }

                if (isValidCell(neighborX, neighborY, width, height, mapData)) {
                    String neighborKey = neighborX + "," + neighborY;
                    Node neighborNode = allNodes.getOrDefault(neighborKey, new Node(neighborX, neighborY));
                    allNodes.putIfAbsent(neighborKey, neighborNode);

                    int newDistance = currentNode.distance + 1; // Pondération de déplacement

                    if (newDistance < neighborNode.distance) {
                        neighborNode.distance = newDistance;
                        neighborNode.previous = currentNode;
                        neighborNode.direction = dir;
                        queue.add(neighborNode);
                    }
                }
            }
        }

        System.out.println("No path found.");
        return null;
    }

    private static boolean isValidCell(int x, int y, int width, int height, int[][] map) {
        boolean isValid = x >= 0 && x < width && y >= 0 && y < height && map[y][x] == 0;
        return isValid;
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