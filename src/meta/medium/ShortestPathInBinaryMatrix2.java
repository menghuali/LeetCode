package meta.medium;

import java.util.Arrays;

/**
 * 1091. Shortest Path in Binary Matrix
 * 
 * Dijkstra’s Algorithm
 * Array solution
 */
public class ShortestPathInBinaryMatrix2 {
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
                        new int[] { 1, 0, 0 },
                        new int[] { 1, 1, 0 },
                        new int[] { 1, 1, 0 }
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
        int[] distance = new int[grid.length * grid.length];
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[0] = 1;
        int[] toVisit = new int[distance.length];
        int i = 0, j = 1;
        while (i < j) {
            int position = toVisit[i];
            j = adjacent(position, grid, distance, toVisit, j);
            i++;
        }
        return distance[distance.length - 1] == Integer.MAX_VALUE ? -1 : distance[distance.length - 1];
    }

    private static int adjacent(int position, int[][] grid, int[] distance, int[] toVisit, int size) {
        int i = position / grid.length;
        int j = position % grid.length;
        int shortest = distance[position];
        size = adjacent(i, j + 1, shortest, grid, distance, toVisit, size);
        size = adjacent(i + 1, j + 1, shortest, grid, distance, toVisit, size);
        size = adjacent(i + 1, j, shortest, grid, distance, toVisit, size);
        size = adjacent(i + 1, j - 1, shortest, grid, distance, toVisit, size);
        size = adjacent(i, j - 1, shortest, grid, distance, toVisit, size);
        size = adjacent(i - 1, j - 1, shortest, grid, distance, toVisit, size);
        size = adjacent(i - 1, j, shortest, grid, distance, toVisit, size);
        size = adjacent(i - 1, j + 1, shortest, grid, distance, toVisit, size);
        return size;
    }

    private static int adjacent(int i, int j, int shortest, int[][] grid, int[] distance, int[] toVisit,
            int size) {
        if (i >= 0 && i < grid.length && j >= 0 && j < grid.length && grid[i][j] == 0) {
            int position = i * grid.length + j;
            if (distance[position] == Integer.MAX_VALUE) {
                distance[position] = shortest + 1;
                toVisit[size] = position;
                return size + 1;
            }
        }
        return size;
    }

}
