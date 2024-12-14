package meta.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 523. Continuous Subarray Sum
 */
public class ContinuousSubarraySumV2 {

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

        Map<Integer, Integer> leftSums = new HashMap<>();
        Map<Integer, Integer> rightSums = new HashMap<>();
        int mid = nums.length / 2;
        boolean goodSubarray = checkLeft(nums, k, 0, mid, leftSums);
        if (goodSubarray)
            return true;

        goodSubarray = checkRight(nums, k, mid, nums.length, rightSums);
        if (goodSubarray)
            return true;

        return checkCombined(leftSums, rightSums);
    }

    private static boolean isGoodSubarray(int sum, int k) {
        return sum % k == 0;
    }

    private static boolean checkLeft(int[] nums, int k, int begin, int end, Map<Integer, Integer> leftSums) {
        int size = end - begin;
        if (size == 1) {
            put(nums[begin], k, leftSums);
            return false;
        }

        if (size == 2) {
            int sum = nums[begin] + nums[begin + 1];
            if (isGoodSubarray(sum, k))
                return true;
            put(nums[begin + 1], k, leftSums);
            put(sum, k, leftSums);
            return false;
        }

        int mid = (begin + end) / 2;
        boolean goodSubarray = checkLeft(nums, k, begin, mid, leftSums);
        if (goodSubarray)
            return true;

        Map<Integer, Integer> rightSums = new HashMap<>();
        goodSubarray = checkRight(nums, k, mid, end, rightSums);
        if (goodSubarray)
            return true;

        goodSubarray = checkCombined(leftSums, rightSums);
        if (goodSubarray)
            return true;

        combineSums(nums, mid, end, leftSums, 1, k);
        return false;
    }

    private static void put(int num, int k, Map<Integer, Integer> map) {
        int remainder = num % k;
        if (remainder == 0) {
            map.put(0, 0);
        } else {
            map.put(remainder, k - remainder);
        }
    }

    private static boolean checkRight(int[] nums, int k, int begin, int end, Map<Integer, Integer> rightSums) {
        int size = end - begin;
        if (size == 1) {
            put(nums[begin], k, rightSums);
            return false;
        }

        if (size == 2) {
            int sum = nums[begin] + nums[begin + 1];
            if (isGoodSubarray(sum, k))
                return true;
            put(nums[begin], k, rightSums);
            put(sum, k, rightSums);
            return false;
        }

        int mid = (begin + end) / 2;
        Map<Integer, Integer> leftSums = new HashMap<>();
        boolean goodSubarray = checkLeft(nums, k, begin, mid, leftSums);
        if (goodSubarray)
            return true;

        goodSubarray = checkRight(nums, k, mid, end, rightSums);
        if (goodSubarray)
            return true;

        goodSubarray = checkCombined(leftSums, rightSums);
        if (goodSubarray)
            return true;

        combineSums(nums, begin, mid, rightSums, 2, k);
        return false;
    }

    private static boolean checkCombined(Map<Integer, Integer> leftSums, Map<Integer, Integer> rightSums) {
        if (leftSums.size() < rightSums.size())
            for (Integer val : leftSums.values()) {
                if (rightSums.containsKey(val))
                    return true;
            }
        else
            for (Integer val : rightSums.values()) {
                if (leftSums.containsKey(val))
                    return true;
            }

        return false;
    }

    private static void combineSums(int[] nums, int begin, int end, Map<Integer, Integer> sums, int direction, int k) {
        List<Integer> keys = new ArrayList<>(sums.keySet());
        sums.clear();
        int sum = 0;
        if (direction == 1)
            for (int i = end - 1; i >= begin; i--) {
                sum += nums[i];
                put(sum, k, sums);
            }
        else
            for (int i = begin; i < end; i++) {
                sum += nums[i];
                put(sum, k, sums);
            }
        for (Integer key : keys) {
            put(key + sum, k, sums);
        }
    }

}
