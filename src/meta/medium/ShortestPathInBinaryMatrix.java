package meta.medium;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 1091. Shortest Path in Binary Matrix
 * 
 * Dijkstra’s Algorithm
 * Queue solution
 */
public class ShortestPathInBinaryMatrix {
    public static void main(String[] args) {
        int[][][] test = new int[][][] {
                new int[][] {
                        new int[] { 0, 1 },
                        new int[] { 1, 0 }
                },
                new int[][] {
                        new int[] { 0, 0 },
                        new int[] { 1, 0 }
                },
                new int[][] {
                        new int[] { 0, 0, 0 },
                        new int[] { 1, 0, 0 },
                        new int[] { 1, 0, 0 }
                },
                new int[][] {
                        new int[] { 0, 0, 0 },
                        new int[] { 1, 1, 0 },
                        new int[] { 1, 0, 0 }
                },
                new int[][] {
                        new int[] { 0, 1, 0, 0, 0, 0 },
                        new int[] { 0, 1, 1, 1, 1, 1 },
                        new int[] { 0, 0, 0, 0, 1, 1 },
                        new int[] { 0, 1, 0, 0, 0, 1 },
                        new int[] { 1, 0, 0, 1, 0, 1 },
                        new int[] { 0, 0, 1, 0, 1, 0 }
                },
                new int[][] {
                        new int[] { 0, 1, 0, 0, 1, 1, 0 },
                        new int[] { 1, 0, 0, 0, 0, 0, 0 },
                        new int[] { 1, 0, 0, 1, 1, 1, 1 },
                        new int[] { 0, 1, 0, 0, 0, 0, 0 },
                        new int[] { 1, 0, 0, 0, 0, 0, 1 },
                        new int[] { 1, 0, 0, 1, 0, 0, 0 },
                        new int[] { 1, 0, 1, 0, 0, 1, 0 }
                }
        };

        for (int i = 0; i < test.length; i++) {
            int[][] grid = test[i];
            System.out.println("Test " + i + ": grid=");
            print(grid);
            System.out.println(shortestPathBinaryMatrix(grid));
            System.out.println();
        }
    }

    private static void print(int[][] grid) {
        for (int i = 0; i < grid.length; i++) {
            System.out.println(Arrays.toString(grid[i]));
        }
    }

    public static int shortestPathBinaryMatrix(int[][] grid) {
        if (grid[0][0] == 1 || grid[grid.length - 1][grid.length - 1] == 1) {
            return -1;
        }

        int[][] distance = new int[grid.length][grid.length];
        for (int i = 0; i < distance.length; i++) {
            Arrays.fill(distance[i], Integer.MAX_VALUE);
        }
        Queue<Vertex> notVisited = new LinkedList<>();
        notVisited.add(new Vertex(0, 0));
        distance[0][0] = 1;
        int width = grid.length - 1;
        while (!notVisited.isEmpty()) {
            if (distance[width][width] < Integer.MAX_VALUE) {
                return distance[width][width];
            }
            Vertex vertex = notVisited.poll();
            adjacent(vertex, grid, distance, notVisited);
        }
        int shortestPath = distance[width][width];
        return shortestPath < Integer.MAX_VALUE ? shortestPath : -1;
    }

    private static void adjacent(Vertex vertex, int[][] grid, int[][] distance, Queue<Vertex> notVisited) {
        int currentDistance = distance[vertex.i][vertex.j];
        addAdjacent(vertex.i, vertex.j + 1, currentDistance, grid, distance, notVisited);
        addAdjacent(vertex.i + 1, vertex.j + 1, currentDistance, grid, distance, notVisited);
        addAdjacent(vertex.i + 1, vertex.j, currentDistance, grid, distance, notVisited);
        addAdjacent(vertex.i + 1, vertex.j - 1, currentDistance, grid, distance, notVisited);
        addAdjacent(vertex.i, vertex.j - 1, currentDistance, grid, distance, notVisited);
        addAdjacent(vertex.i - 1, vertex.j - 1, currentDistance, grid, distance, notVisited);
        addAdjacent(vertex.i - 1, vertex.j, currentDistance, grid, distance, notVisited);
        addAdjacent(vertex.i - 1, vertex.j + 1, currentDistance, grid, distance, notVisited);
    }

    private static void addAdjacent(int i, int j, int currentDistance, int grid[][], int[][] distance,
            Queue<Vertex> adjacent) {
        if (i >= 0 && i < grid.length && j >= 0 && j < grid.length && grid[i][j] == 0
                && distance[i][j] == Integer.MAX_VALUE) {
            distance[i][j] = currentDistance + 1;
            adjacent.add(new Vertex(i, j));
        }
    }

    private static class Vertex {
        int i;
        int j;

        public Vertex(int i, int j) {
            this.i = i;
            this.j = j;
        }

    }

}
