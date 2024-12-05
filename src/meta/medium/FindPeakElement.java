package meta.medium;

import java.util.Arrays;
import java.util.function.Function;

public class FindPeakElement {

    public static int findPeakElement(int[] nums) {
        if (nums.length == 1)
            return 0;
        return findPeakElement(nums, 0, nums.length - 1);
    }

    private static int findPeakElement(int[] nums, int start, int end) {
        if (start == end) {
            return isPeakElement(nums, start) ? start : -1;
        } else if (start == end - 1) {
            if (isPeakElement(nums, start))
                return start;
            else
                return isPeakElement(nums, end) ? end : -1;
        } else {
            int middle = (start + end) / 2;
            int peak = findPeakElement(nums, start, middle);
            if (peak < 0)
                return findPeakElement(nums, middle + 1, end);
            else
                return peak;
        }
    }

    private static boolean isPeakElement(int[] nums, int index) {
        int before = index - 1 >= 0 ? nums[index - 1] : Integer.MIN_VALUE;
        int after = index + 1 < nums.length ? nums[index + 1] : Integer.MIN_VALUE;
        return nums[index] > before && nums[index] > after;
    }

    public static void main(String[] args) {
        test("findPeakElement", FindPeakElement::findPeakElement);
    }

    private static void test(String name, Function<int[], Integer> solution) {
        System.out.println("== " + name + " ==");
        int[][] testcases = new int[][] { new int[] { 1, 2, 3, 1 }, new int[] { 1, 2, 1, 3, 5, 6, 4 } };
        for (int[] testcase : testcases) {
            System.out.println("Input: " + Arrays.toString(testcase));
            System.out.println("Output: " + solution.apply(testcase));
        }
    }

}
