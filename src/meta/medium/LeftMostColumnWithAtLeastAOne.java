package meta.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 1428. Leftmost Column with at Least a One
 */
public class LeftMostColumnWithAtLeastAOne {

    public static int leftMostColumnWithOne(BinaryMatrix binaryMatrix) {
        int mostLeft = binaryMatrix.dimensions().get(1);
        for (int i = 0; i < binaryMatrix.dimensions().get(0); i++) {
            int left = rowLeftMost(binaryMatrix, i, 0, mostLeft);
            if (left != -1) {
                mostLeft = Math.min(left, mostLeft);
            }
            if (mostLeft == 0) {
                break;
            }
        }
        return mostLeft == binaryMatrix.dimensions().get(1) ? -1 : mostLeft;
    }

    private static int rowLeftMost(BinaryMatrix binaryMatrix, int row, int begin, int end) {
        if (end - begin == 1) {
            return binaryMatrix.get(row, begin) == 1 ? begin : -1;
        }
        int mid = (begin + end) / 2;
        if (binaryMatrix.get(row, mid) == 1) {
            int leftMost = rowLeftMost(binaryMatrix, row, begin, mid);
            return leftMost == -1 ? mid : leftMost;
        } else {
            return (mid + 1 < end) ? rowLeftMost(binaryMatrix, row, mid + 1, end) : -1;
        }
    }

    public static void main(String[] args) {
        int test[][][] = new int[][][] {
                new int[][] {
                        new int[] { 0, 0 },
                        new int[] { 1, 1 }
                },
                new int[][] {
                        new int[] { 0, 0 },
                        new int[] { 0, 1 }
                },
                new int[][] {
                        new int[] { 0, 0 },
                        new int[] { 0, 0 }
                },
                new int[][] {
                        new int[] { 0, 0, 0, 1, 1 },
                        new int[] { 0, 0, 0, 0, 0 },
                        new int[] { 0, 1, 1, 1, 1 },
                        new int[] { 0, 0, 0, 0, 0 },
                }
        };
        for (int i = 0; i < test.length; i++) {
            int[][] matrix = test[i];
            System.out.println(String.format("Test %d: %d", i, leftMostColumnWithOne(new BinaryMatrixImpl(matrix))));
            print(matrix);
            System.out.println("leftMost=" + leftMostColumnWithOne(new BinaryMatrixImpl(matrix)));
            System.out.println();
        }
    }

    private static void print(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            System.out.println(Arrays.toString(matrix[i]));
        }
    }

    interface BinaryMatrix {
        public int get(int row, int col);

        public List<Integer> dimensions();
    };

    static class BinaryMatrixImpl implements BinaryMatrix {
        int[][] matrix;
        List<Integer> dimensions;

        BinaryMatrixImpl(int[][] matrix) {
            this.matrix = matrix;
            dimensions = new ArrayList<>(2);
            dimensions.add(matrix.length);
            dimensions.add(matrix[0].length);
        }

        @Override
        public int get(int row, int col) {
            return matrix[row][col];
        }

        @Override
        public List<Integer> dimensions() {
            return dimensions;
        }

    }

}
