package meta.medium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * 398. Random Pick Index
 * 
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(nums);
 * int param_1 = obj.pick(target);
 */
public class RandomPickIndex {
    public static void main(String[] args) {
        
    }

    private Map<Integer, List<Integer>> map;
    private Random random;

    public RandomPickIndex(int[] nums) {
        random = new Random();
        map = new HashMap<>((int) Math.round(nums.length / 0.75));
        for (int i : nums) {
            List<Integer> indexes = new ArrayList<>();
            if (map.containsKey(nums[i])) {
                indexes = map.get(nums[i]);
            } else {
                indexes = new ArrayList<>();
                map.put(nums[i], indexes);
            }
            indexes.add(i);
        }
    }

    public int pick(int target) {
        List<Integer> indexes = map.get(target);
        return indexes.get(random.nextInt(indexes.size()));
    }
}
