package meta.medium;

import java.util.Arrays;
import java.util.HashMap;

/**
 * 560. Subarray Sum Equals K
 */
public class SubarraySumEqualsK {

    public static int subarraySum(int[] nums, int k) {
        HashMap<Integer, Integer> prefixSums = new HashMap<>();
        prefixSums.put(0, 1);
        int totalSubarrays = 0;
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            int prefixSum = sum - k;
            if (prefixSums.containsKey(prefixSum)) {
                int prefixSumCount = prefixSums.get(prefixSum);
                totalSubarrays += prefixSumCount;
            }
            int sumCount = 0;
            if (prefixSums.containsKey(sum)) {
                sumCount = prefixSums.get(sum);
            }
            sumCount++;
            prefixSums.put(sum, sumCount);
        }
        return totalSubarrays;
    }

    public static void main(String[] args) {
        Object[][] test = new Object[][] {
                new Object[] { new int[] { 1, 1, 1 }, 2 },
                new Object[] { new int[] { 1, 2, 3 }, 3 },
                new Object[] { new int[] { 1, -1, 0 }, 0 }
        };
        for (int i = 0; i < test.length; i++) {
            int[] nums = (int[]) test[i][0];
            int k = (int) test[i][1];
            System.out.println("Input " + i + ": nums=" + Arrays.toString(nums) + ", k=" + k);
            System.out.println(subarraySum(nums, k));
        }
    }

}
