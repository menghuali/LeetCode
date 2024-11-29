package meta.easy;

import java.util.ArrayList;
import java.util.List;

/**
 * You are given an inclusive range [lower, upper] and a sorted unique integer
 * array nums, where all elements are within the inclusive range.
 * 
 * A number x is considered missing if x is in the range [lower, upper] and x is
 * not in nums.
 * 
 * Return the shortest sorted list of ranges that exactly covers all the missing
 * numbers. That is, no element of nums is included in any of the ranges, and
 * each missing number is covered by one of the ranges.
 * 
 * 
 * Example 1:
 * 
 * Input: nums = [0,1,3,50,75], lower = 0, upper = 99
 * Output: [[2,2],[4,49],[51,74],[76,99]]
 * Explanation: The ranges are:
 * [2,2]
 * [4,49]
 * [51,74]
 * [76,99]
 * Example 2:
 * 
 * Input: nums = [-1], lower = -1, upper = -1
 * Output: []
 * Explanation: There are no missing ranges since there are no missing numbers.
 * 
 * 
 * Constraints:
 * 
 * -109 <= lower <= upper <= 109
 * 0 <= nums.length <= 100
 * lower <= nums[i] <= upper
 * All the values of nums are unique.
 */
public class MissingRanges {

    public static List<List<Integer>> findMissingRanges_1(int[] nums, int lower, int upper) {
        List<List<Integer>> missingRanges = new ArrayList<>(nums.length + 2);
        if (nums.length == 0) {
            ArrayList<Integer> range = new ArrayList<>(3);
            range.add(lower);
            range.add(upper);
            missingRanges.add(range);
            return missingRanges;
        }

        if (lower < nums[0]) {
            ArrayList<Integer> range = new ArrayList<>(3);
            range.add(lower);
            range.add(nums[0] - 1);
            missingRanges.add(range);
        }

        for (int left = 0, right = 1; right < nums.length; left++, right++) {
            if (nums[left] + 1 == nums[right]) {
                continue;
            }
            ArrayList<Integer> range = new ArrayList<>(3);
            range.add(nums[left] + 1);
            range.add(nums[right] - 1);
            missingRanges.add(range);
        }

        if (nums[nums.length - 1] < upper) {
            ArrayList<Integer> range = new ArrayList<>(3);
            range.add(nums[nums.length - 1] + 1);
            range.add(upper);
            missingRanges.add(range);
        }
        return missingRanges;
    }

    public static void main(String[] args) {
        System.out.println(findMissingRanges_1(new int[] {0,1,3,50,75}, 0, 99));
        System.out.println(findMissingRanges_1(new int[] {-1}, -1, -1));
    }

}
