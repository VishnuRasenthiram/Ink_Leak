package universite_paris8.iut.ink_leak.Modele;

import java.util.*;

public class Dijkstra {

    public static List<Integer> forceAllCells(int[][] mapData, int startX, int startY, int targetX, int targetY) {
        int width = mapData[0].length;
        int height = mapData.length;

        // Keep track of visited cells
        boolean[][] visited = new boolean[height][width];
        visited[startY][startX] = true;  // Mark starting cell as visited

        // Use a stack to explore cells in a depth-first manner
        Stack<Node> stack = new Stack<>();
        stack.push(new Node(startX, startY));

        List<Integer> path = new ArrayList<>();

        while (!stack.isEmpty()) {
            Node currentNode = stack.pop();

            // Add current cell direction to path
            path.add(getDirection(currentNode, startX, startY));

            if (currentNode.x == targetX && currentNode.y == targetY) {
                // Reached target cell, return path
                return path;
            }

            // Explore unvisited neighbors in order (up, right, down, left)
            for (int[] direction : new int[][]{{0, -1, 1}, {1, 0, 2}, {0, 1, 3}, {-1, 0, 4}}) {
                int neighborX = currentNode.x + direction[0];
                int neighborY = currentNode.y + direction[1];

                if (isValidCell(neighborX, neighborY, width, height, mapData) && !visited[neighborY][neighborX]) {
                    visited[neighborY][neighborX] = true;
                    stack.push(new Node(neighborX, neighborY));
                }
            }
        }

        System.out.println("No path found.");
        return null;
    }

    private static boolean isValidCell(int x, int y, int width, int height, int[][] map) {
        return x >= 0 && x < width && y >= 0 && y < height && map[y][x] == 0;
    }

    // Helper function to determine direction based on current and starting positions
    private static int getDirection(Node node, int startX, int startY) {
        if (node.x > startX) {
            return 2; // Right
        } else if (node.x < startX) {
            return 4; // Left
        } else if (node.y > startY) {
            return 3; // Down
        } else {
            return 1; // Up (assuming starting position is a unique entry point)
        }
    }
}

class Node {
    int x;
    int y;

    public Node(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
