package meta.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

public class MergeIntervals {
    public static int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, (x, y) -> Integer.compare(x[0], y[0]));
        return merge_DivideAndConquer(intervals);
    }

    private static int[][] merge_DivideAndConquer(int[][] intervals) {
        if (intervals.length == 1)
            return intervals;

        if (intervals.length == 2) {
            if (intervals[0][1] >= intervals[1][0])
                return new int[][] {
                        { Math.min(intervals[0][0], intervals[1][0]), Math.max(intervals[0][1], intervals[1][1]) } };
            else
                return intervals;
        }

        int[][] left = new int[intervals.length / 2][];
        for (int i = 0; i < intervals.length / 2; i++) {
            left[i] = intervals[i];
        }
        int[][] right = new int[intervals.length - left.length][];
        for (int i = 0; i < right.length; i++) {
            right[i] = intervals[left.length + i];
        }
        left = merge_DivideAndConquer(left);
        right = merge_DivideAndConquer(right);
        int[] lastLeft = left[left.length - 1];
        int merged = 0;
        for (; merged < right.length; merged++) {
            if (lastLeft[1] >= right[merged][0]) {
                lastLeft[0] = Math.min(lastLeft[0], right[merged][0]);
                lastLeft[1] = Math.max(lastLeft[1], right[merged][1]);
            } else
                break;
        }
        int[][] merges = new int[left.length + right.length - merged][];
        for (int i = 0; i < left.length - (merged > 0 ? 1 : 0); i++) {
            merges[i] = left[i];
        }
        merges[left.length - 1] = lastLeft;
        for (int i = merged, j = left.length; i < right.length; i++, j++) {
            merges[j] = right[i];
        }
        return merges;
    }

    public static int[][] merge_BrutalForce(int[][] intervals) {
        if (intervals.length == 1)
            return intervals;
        Arrays.sort(intervals, (x, y) -> Integer.compare(x[0], y[0]));
        int[] temp = new int[2];
        temp[0] = intervals[0][0];
        temp[1] = intervals[0][1];
        ArrayList<int[]> merged = new ArrayList<>();
        for (int i = 1; i < intervals.length; i++) {
            if (temp[1] >= intervals[i][0]) {
                temp[0] = Math.min(temp[0], intervals[i][0]);
                temp[1] = Math.max(temp[1], intervals[i][1]);
            } else {
                merged.add(temp);
                temp = new int[] { intervals[i][0], intervals[i][1] };
            }
        }
        merged.add(temp);
        int[][] result = new int[merged.size()][];
        for (int i = 0; i < result.length; i++) {
            result[i] = merged.get(i);
        }
        return result;
    }

    public static int[][] merge_BST(int[][] intervals) {
        if (intervals.length <= 1)
            return intervals;
        Node root = new Node(intervals[0]);
        for (int i = 1; i < intervals.length; i++) {
            Node child = new Node(intervals[i]);
            root.add(child);
        }
        ArrayList<int[]> merged = new ArrayList<>(intervals.length);
        root.exportInternvals(merged);
        int[][] result = new int[merged.size()][];
        for (int i = 0; i < result.length; i++) {
            result[i] = merged.get(i);
        }
        return result;
    }

    private static class Node {
        Node left;
        Node right;
        int[] interval;

        Node(int[] interval) {
            this.interval = interval;
        }

        public void exportInternvals(ArrayList<int[]> export) {
            if (left != null) {
                left.exportInternvals(export);
            }
            export.add(interval);
            if (right != null) {
                right.exportInternvals(export);
            }
        }

        int compare(Node another) {
            if (interval[1] < another.interval[0])
                return -1;
            else if (another.interval[1] < interval[0])
                return 1;
            else
                return 0;
        }

        void merge(Node another) {
            interval[0] = Math.min(interval[0], another.interval[0]);
            interval[1] = Math.max(interval[1], another.interval[1]);
        }

        void add(Node another) {
            int compare = compare(another);
            if (compare == 0) {
                merge(another);
                mergeLeftBranch();
                mergeRightBranch();
            } else if (compare > 0)
                if (left != null)
                    left.add(another);
                else
                    left = another;
            else {
                if (right != null)
                    right.add(another);
                else
                    right = another;
            }
        }

        private void mergeLeftBranch() {
            while (left != null && compare(left) == 0) {
                merge(left);
                left = left.left;
            }
            if (left == null)
                return;
            Node parent = left;
            Node child = parent.right;
            while (child != null) {
                if (compare(child) == 0) {
                    merge(child);
                    parent.right = child.left;
                    child = child.left;
                } else {
                    parent = child;
                    child = parent.right;
                }
            }
        }

        private void mergeRightBranch() {
            while (right != null && compare(right) == 0) {
                merge(right);
                right = right.right;
            }
            if (right == null) 
                return;
            Node parent = right;
            Node child = parent.left;
            while (child != null) {
                if (compare(child) == 0) {
                    merge(child);
                    parent.left = child.right;
                    child = child.right;
                } else {
                    parent = child;
                    child = parent.left;
                }
            }
        }

    }

    public static int[][] merge_SortAndMerge(int[][] intervals) {
        if (intervals.length <= 1)
            return intervals;
        Arrays.sort(intervals, (x, y) -> Integer.compare(x[0], y[0]));
        List<int[]> merged = new ArrayList<>(intervals.length + 1);
        int[] left = intervals[0]; //[1, 3], [2, 6], [8, 10], [15, 18]
        for (int i = 1; i < intervals.length; i++) {
            int[] right = intervals[i];
            if (left[1] >= right[0]) {
                left[0] = Math.min(left[0], right[0]);
                left[1] = Math.max(left[1], right[1]);
            } else {
                merged.add(left);
                left = right;
            }
        }
        merged.add(left);
        int[][] result = new int[merged.size()][];
        for (int i = 0; i < result.length; i++) {
            result[i] = merged.get(i);
        }
        return result;
    }

    public static void main(String[] args) {
        test("DivideAndConquer", MergeIntervals::merge_SortAndMerge);
    }

    private static void test(String name, Function<int[][], int[][]> solution) {
        System.out.println("=== " + name + " ===");
        execute(solution, new int[][] { { 1, 3 }, { 2, 6 }, { 8, 10 }, { 15, 18 } });
        execute(solution, new int[][] { { 1, 4 }, { 4, 5 } });
        execute(solution, new int[][] { { 1, 4 }, { 0, 4 } });
        execute(solution, new int[][] { { 1, 4 }, { 0, 0 } });
        execute(solution, new int[][] { { 1, 4 }, { 0, 2 }, { 3, 6 } });
        execute(solution, new int[][] { { 2, 3 }, { 4, 5 }, { 6, 7 }, { 8, 9 }, { 1,
                10 } });
        execute(solution,
                new int[][] { { 5, 7 }, { 5, 5 }, { 1, 1 }, { 0, 0 }, { 3, 3 }, { 4, 5 }, { 1, 1 }, { 3, 4 } });
    }

    private static void execute(Function<int[][], int[][]> solution, int[][] input) {
        System.out.println("Input: " + toString(input));
        System.out.println("Merge: " + toString(solution.apply(input)));
    }

    private static String toString(int[][] ranges) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < ranges.length; i++) {
            sb.append(Arrays.toString(ranges[i])).append(", ");
        }
        sb.delete(sb.length() - 2, sb.length());
        sb.append("]");
        return sb.toString();
    }

}
