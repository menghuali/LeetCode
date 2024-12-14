package meta.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 523. Continuous Subarray Sum
 */
public class ContinuousSubarraySum {

    public static void main(String[] args) {
        Object[][] test = new Object[][] {
                new Object[] { new int[] { 23, 2, 6, 4, 7 }, 6 },
                new Object[] { new int[] { 23, 2, 6, 4, 7 }, 6 },
                new Object[] { new int[] { 23, 2, 6, 4, 7 }, 13 },
                new Object[] { new int[] { 0, 0 }, 1 } };
        for (int i = 0; i < test.length; i++) {
            int[] nums = (int[]) test[i][0];
            int k = (int) test[i][1];
            System.out.println("Input " + i + ": " + Arrays.toString(nums) + ", k=" + k);
            System.out.println("Output " + i + ": " + checkSubarraySum(nums, k));
        }
    }

    public static boolean checkSubarraySum(int[] nums, int k) {
        if (nums.length == 1)
            return false;

        if (nums.length == 2)
            return isGoodSubarray(nums[0] + nums[1], k);

        List<Integer> leftSums = new ArrayList<>(nums.length / 2);
        List<Integer> rightSums = new ArrayList<>(nums.length / 2 + 1);
        int mid = nums.length / 2;
        boolean goodSubarray = checkLeft(nums, k, 0, mid, leftSums);
        if (goodSubarray)
            return true;

        goodSubarray = checkRight(nums, k, mid, nums.length, rightSums);
        if (goodSubarray)
            return true;

        return checkCombined(k, leftSums, rightSums);
    }

    private static boolean isGoodSubarray(int sum, int k) {
        return sum % k == 0;
    }

    private static boolean checkLeft(int[] nums, int k, int begin, int end, List<Integer> leftSums) {
        int size = end - begin;
        if (size == 1) {
            leftSums.add(nums[begin]);
            return false;
        }

        if (size == 2) {
            int sum = nums[begin] + nums[begin + 1];
            if (isGoodSubarray(sum, k))
                return true;
            leftSums.add(nums[begin + 1] % k);
            leftSums.add(sum % k);
            return false;
        }

        int mid = (begin + end) / 2;
        boolean goodSubarray = checkLeft(nums, k, begin, mid, leftSums);
        if (goodSubarray)
            return true;

        List<Integer> rightSums = new ArrayList<>(end - mid);
        goodSubarray = checkRight(nums, k, mid, end, rightSums);
        if (goodSubarray)
            return true;

        goodSubarray = checkCombined(k, leftSums, rightSums);
        if (goodSubarray)
            return true;

        combineSums(nums, mid, end, leftSums, 1, k);
        return false;
    }

    private static boolean checkRight(int[] nums, int k, int begin, int end, List<Integer> rightSums) {
        int size = end - begin;
        if (size == 1) {
            rightSums.add(nums[begin]);
            return false;
        }

        if (size == 2) {
            int sum = nums[begin] + nums[begin + 1];
            if (isGoodSubarray(sum, k))
                return true;
            rightSums.add(nums[begin] % k);
            rightSums.add(sum % k);
            return false;
        }

        int mid = (begin + end) / 2;
        List<Integer> leftSums = new ArrayList<>(mid - begin);
        boolean goodSubarray = checkLeft(nums, k, begin, mid, leftSums);
        if (goodSubarray)
            return true;

        goodSubarray = checkRight(nums, k, mid, end, rightSums);
        if (goodSubarray)
            return true;

        goodSubarray = checkCombined(k, leftSums, rightSums);
        if (goodSubarray)
            return true;

        combineSums(nums, begin, mid, rightSums, 2, k);
        return false;
    }

    private static boolean checkCombined(int k, List<Integer> leftSums, List<Integer> rightSums) {
        for (int i = 0; i < leftSums.size(); i++) {
            int left = leftSums.get(i);
            for (int j = 0; j < rightSums.size(); j++)
                if ((left + rightSums.get(j)) % k == 0)
                    return true;
        }
        return false;
    }

    private static void combineSums(int[] nums, int begin, int end, List<Integer> sums, int direction, int k) {
        List<Integer> copy = new ArrayList<>(sums.size());
        copy.addAll(sums);
        sums.clear();
        int sum = 0;
        if (direction == 1)
            for (int i = end - 1; i >= begin; i--) {
                sum += nums[i];
                sums.add(sum % k);
            }
        else
            for (int i = begin; i < end; i++) {
                sum += nums[i];
                sums.add(sum % k);
            }
        for (int i = 0; i < copy.size(); i++) {
            sums.add(copy.get(i) + sum);
        }
    }

}
