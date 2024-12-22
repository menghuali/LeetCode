package meta.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class KClosestPointsToOrigin {

    public static void main(String[] args) {
        Object[][] test = new Object[][] {
                new Object[] { new int[][] {
                        new int[] { 3, 3 }, new int[] { 5, -1 }, new int[] { -2, 4 } },
                        2,
                        "[[3,3],[-2,4]]" },
                new Object[] { new int[][] {
                        new int[] { 1, 1 }, new int[] { 10, 10 }, new int[] { 2, 2 }, new int[] { 3, 3 },
                        new int[] { 20, 20 }, new int[] { 4, 4 }, new int[] { 5, 6 } },
                        3,
                        "[[1,1],[2,2],[3,3]]" }
        };
        for (int i = 0; i < test.length; i++) {
            int[][] points = (int[][]) test[i][0];
            int k = (int) test[i][1];
            String expect = (String) test[i][2];
            System.out.println(String.format("Test %d: points=%s, k=%d \nexpect=%s \nactual=%s", i,
                    pointsToString(points), k, expect, pointsToString(kClosest(points, k))));
        }
    }

    private static Object pointsToString(int[][] points) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < points.length; i++) {
            sb.append(Arrays.toString(points[i])).append(",");
        }
        sb.deleteCharAt(sb.length() - 1);
        sb.append(']');
        return sb.toString();
    }

    private static final Random R = new Random();

    public static int[][] kClosest(int[][] points, int k) {
        List<Point> _points = new ArrayList<>();
        for (int i = 0; i < points.length; i++) {
            _points.add(new Point(points[i]));
        }
        List<Point> kClosest = kClosest(_points, k);
        int[][] result = new int[k][];
        for (int i = 0; i < k; i++) {
            result[i] = kClosest.get(i).coordinate;
        }
        return result;
    }

    private static List<Point> kClosest(List<Point> points, int k) {
        if (points.size() == k) {
            return points;
        }

        Point pivot = points.get(R.nextInt(points.size()));
        List<Point> shortter = new ArrayList<>();
        List<Point> longer = new ArrayList<>();
        for (Point point : points) {
            if (point.distance <= pivot.distance) {
                shortter.add(point);
            } else {
                longer.add(point);
            }
        }
        if (shortter.size() > k) {
            return kClosest(shortter, k);
        } else if (shortter.size() == k) {
            return shortter;
        } else {
            shortter.addAll(kClosest(longer, k - shortter.size()));
            return shortter;
        }
    }

    private static class Point {
        int[] coordinate;
        int distance;

        public Point(int[] coordinate) {
            this.coordinate = coordinate;
            distance = coordinate[0] * coordinate[0] + coordinate[1] * coordinate[1];
        }
    }

}
