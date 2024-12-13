package meta.medium;

import java.util.Arrays;
import java.util.function.Function;

public class DiagonalTraverse {
    public static void main(String[] args) {
        test("default", DiagonalTraverse::findDiagonalOrder);
    }

    private static void test(String name, Function<int[][], int[]> solution) {
        System.out.println("== " + name + " ==");
        int[][][] test = new int[][][] {
                new int[][] { new int[] { 1, } },
                new int[][] { new int[] { 1, 2 } },
                new int[][] {
                        new int[] { 1 },
                        new int[] { 2 } },
                new int[][] {
                        new int[] { 1, 2, 3 },
                        new int[] { 4, 5, 6 },
                        new int[] { 7, 8, 9 } },
                new int[][] {
                        new int[] { 1, 2, 3 },
                        new int[] { 4, 5, 6 },
                        new int[] { 7, 8, 9 },
                        new int[] { 10, 11, 12 },
                        new int[] { 13, 14, 15 },
                        new int[] { 16, 17, 18 } },
                new int[][] {
                        new int[] { 1, 2, 3, 4, 5, 6 },
                        new int[] { 7, 8, 9, 10, 11, 12 },
                        new int[] { 13, 14, 15, 16, 17, 18 }
                }
        };
        for (int i = 0; i < test.length; i++) {
            System.out.println("Test " + i + ": " + Arrays.toString(solution.apply(test[i])));
        }
    }

    public static int[] findDiagonalOrder(int[][] mat) {
        int[] diagonal = new int[mat.length * mat[0].length];
        int i = 0;
        Cursor cursor = new Cursor(mat);
        diagonal[i++] = cursor.getNum();
        while (i < diagonal.length) {
            cursor.turn();
            diagonal[i++] = cursor.getNum();
            while (cursor.keepMove()) {
                cursor.move();
                diagonal[i++] = cursor.getNum();
            }
        }
        return diagonal;
    }

    private static class Cursor {
        boolean up;
        int x;
        int y;
        int width;
        int height;
        int[][] mat;

        Cursor(int[][] mat) {
            this.mat = mat;
            width = mat[0].length;
            height = mat.length;
            x = 0;
            y = 0;
            up = true;
        }

        int getNum() {
            return mat[y][x];
        }

        void turn() {
            up = !up;
            if (up) {
                if (x == 0 && y < height - 1)
                    y++;
                else
                    x++;
            } else {
                if (y == 0 && x < width - 1)
                    x++;
                else
                    y++;
            }
        }

        void move() {
            if (up) {
                x++;
                y--;
            } else {
                x--;
                y++;
            }
        }

        boolean keepMove() {
            if (up) {
                return y > 0 && x < width - 1;
            } else {
                return x > 0 && y < height - 1;
            }
        }
    }

}
