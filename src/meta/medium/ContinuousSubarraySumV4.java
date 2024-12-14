package meta.medium;

import java.util.Arrays;
import java.util.HashMap;

/**
 * 523. Continuous Subarray Sum
 * 
 * Sum prefix
 */
public class ContinuousSubarraySumV4 {

    public static boolean checkSubarraySum(int[] nums, int k) {
        int prefixMod = 0;
        HashMap<Integer, Integer> modSeen = new HashMap<>();
        modSeen.put(0, -1);

        for (int i = 0; i < nums.length; i++) {
            prefixMod = (prefixMod + nums[i]) % k;

            if (modSeen.containsKey(prefixMod)) {
                // ensures that the size of subarray is at least 2
                if (i - modSeen.get(prefixMod) > 1) {
                    return true;
                }
            } else {
                // mark the value of prefixMod with the current index.
                modSeen.put(prefixMod, i);
            }
        }

        return false;
    }

    public static void main(String[] args) {
        Object[][] test = new Object[][] {
                // new Object[] { new int[] { 23, 2, 6, 4, 7 }, 6 },
                // new Object[] { new int[] { 23, 2, 6, 4, 7 }, 6 },
                // new Object[] { new int[] { 23, 2, 6, 4, 7 }, 13 },
                new Object[] { new int[] { 0, 0 }, 1 },
                // new Object[] { new int[] { 23, 2, 6, 2, 5 }, 6 },
                // new Object[] { new int[] { 23, 2, 4, 6, 6 }, 7 }
        };
        for (int i = 0; i < test.length; i++) {
            int[] nums = (int[]) test[i][0];
            int k = (int) test[i][1];
            System.out.println("Input " + i + ": " + Arrays.toString(nums) + ", k=" + k);
            System.out.println("Output " + i + ": " + checkSubarraySum(nums, k));
        }
    }

}
