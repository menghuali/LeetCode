package meta.medium;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 523. Continuous Subarray Sum
 * 
 * Divide and Conquer
 */
public class ContinuousSubarraySumV3 {
    public static void main(String[] args) {
        Object[][] test = new Object[][] {
                new Object[] { new int[] { 23, 2, 6, 4, 7 }, 6 },
                new Object[] { new int[] { 23, 2, 6, 4, 7 }, 6 },
                new Object[] { new int[] { 23, 2, 6, 4, 7 }, 13 },
                new Object[] { new int[] { 0, 0 }, 1 },
                new Object[] { new int[] { 23, 2, 6, 2, 5 }, 6 },
                new Object[] { new int[] { 23, 2, 4, 6, 6 }, 7 }
        };
        for (int i = 0; i < test.length; i++) {
            int[] nums = (int[]) test[i][0];
            int k = (int) test[i][1];
            System.out.println("Input " + i + ": " + Arrays.toString(nums) + ", k=" + k);
            System.out.println("Output " + i + ": " + checkSubarraySum(nums, k));
        }
    }

    public static boolean checkSubarraySum(int[] nums, int k) {
        return checkSubarraySum(nums, k, 0, nums.length).isGood();
    }

    private static Subarray checkSubarraySum(int[] nums, int k, int begin, int end) {
        int size = end - begin;
        if (size == 1) {
            return new Subarray(false, nums[begin] % k);
        }

        int mid = (begin + end) / 2;
        Subarray left = checkSubarraySum(nums, k, begin, mid);
        if (left.isGood())
            return left;

        Subarray right = checkSubarraySum(nums, k, mid, end);
        if (right.isGood())
            return right;

        if (match(k, left.rightRemainders, right.leftRemainders)) {
            left.setGood(true);
            return left;
        }

        return merge(k, left, right);
    }

    private static Subarray merge(int k, Subarray left, Subarray right) {
        Set<Integer> newLeft = left.leftRemainders;
        for (int remainder : right.leftRemainders) {
            newLeft.add((left.sumRemainder + remainder) % k);
        }
        Set<Integer> newRight = right.rightRemainders;
        for (int remainder : left.rightRemainders) {
            newRight.add((right.sumRemainder + remainder) % k);
        }
        return new Subarray(false, left.sumRemainder + right.sumRemainder, newLeft, newRight);
    }

    private static boolean match(int k, Set<Integer> one, Set<Integer> another) {
        for (int remainder : one) {
            if (remainder == 0 && another.contains(0)) {
                return true;
            } else if (another.contains(k - remainder)) {
                return true;
            }
        }
        return false;
    }

    private static class Subarray {
        boolean good;
        int sumRemainder;
        Set<Integer> leftRemainders;
        Set<Integer> rightRemainders;

        public Subarray(boolean good, int sumRemainder) {
            this.good = good;
            this.sumRemainder = sumRemainder;
            leftRemainders = new HashSet<>();
            leftRemainders.add(sumRemainder);
            rightRemainders = new HashSet<>();
            rightRemainders.add(sumRemainder);
        }

        public Subarray(boolean good, int sumRemainder, Set<Integer> leftRemainders, Set<Integer> rightRemainders) {
            this.good = good;
            this.sumRemainder = sumRemainder;
            this.leftRemainders = leftRemainders;
            this.rightRemainders = rightRemainders;
        }

        public boolean isGood() {
            return good;
        }

        public void setGood(boolean good) {
            this.good = good;
        }
    }

}
