package universite_paris8.iut.ink_leak.Modele;

import java.util.*;
enum Direction {
    UP, DOWN, LEFT, RIGHT
}

class Node {
    int x;
    int y;
    int distance;
    Node previous;
    Direction direction;

    public Node(int x, int y) {
        this.x = x;
        this.y = y;
        this.distance = Integer.MAX_VALUE;
        this.previous = null;
        this.direction = null;
    }
}

public class Dijkstra {

    public static List<Direction> dijkstra(int[][] mapData, int startX, int startY, int targetX, int targetY) {
        int width = mapData[0].length;
        int height = mapData.length;
        for (int i = 0; i < mapData.length; i++) {
            for (int j = 0; j < mapData[i].length; j++) {
                if (mapData[i][j] == 0 || mapData[i][j] == 10) {
                    mapData[i][j] = 0;
                } else{
                    mapData[i][j] = 1;
                }
            }
        }
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

            for (Direction dir : Direction.values()) {
                int neighborX = currentNode.x;
                int neighborY = currentNode.y;

                switch (dir) {
                    case UP:
                        neighborY--;
                        break;
                    case DOWN:
                        neighborY++;
                        break;
                    case LEFT:
                        neighborX--;
                        break;
                    case RIGHT:
                        neighborX++;
                        break;
                }

                if (isValidCell(neighborX, neighborY, width, height, mapData)) {
                    String neighborKey = neighborX + "," + neighborY;
                    Node neighborNode = allNodes.getOrDefault(neighborKey, new Node(neighborX, neighborY));
                    allNodes.putIfAbsent(neighborKey, neighborNode);

                    int newDistance = currentNode.distance + mapData[neighborY][neighborX];

                    if (newDistance < neighborNode.distance) {
                        neighborNode.distance = newDistance;
                        neighborNode.previous = currentNode;
                        neighborNode.direction = dir;
                        queue.add(neighborNode);
                    }
                }
            }
        }

        return null;
    }

    private static boolean isValidCell(int x, int y, int width, int height, int[][] map) {
        return x >= 0 && x < width && y >= 0 && y < height && map[y][x] != 1;
    }

    private static List<Direction> reconstructPath(Node node) {
        List<Direction> path = new ArrayList<>();

        while (node.previous != null) {
            path.add(0, node.direction);
            node = node.previous;
        }

        return path;
    }
}
