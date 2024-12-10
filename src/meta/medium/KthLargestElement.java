package meta.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Random;
import java.util.function.BiFunction;

/**
 * 215. Kth Largest Element in an Array without sorting
 */
public class KthLargestElement {

    public static int findKthLargest_BT(int[] nums, int k) {
        TreeNode node = new TreeNode(nums[0]);
        for (int i = 1; i < nums.length; i++)
            node.addNode(nums[i]);

        int inheritedRightCount = 0;
        while (node != null) {
            if (k > node.rightCount + inheritedRightCount && k <= node.rightCount + inheritedRightCount + node.me) {
                break;
            } else if (node.rightCount + inheritedRightCount >= k) {
                node = node.right;
            } else {
                inheritedRightCount = inheritedRightCount + node.rightCount + node.me;
                node = node.left;
            }
        }
        return node.val;
    }

    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        int rightCount;
        int me;

        TreeNode(int val) {
            this.val = val;
            me = 1;
        }

        void addNode(int newVal) {
            if (newVal < val) {
                if (left == null) {
                    left = new TreeNode(newVal);
                } else
                    left.addNode(newVal);
            } else if (val == newVal) {
                me++;
            } else {
                if (right == null) {
                    right = new TreeNode(newVal);
                } else {
                    right.addNode(newVal);
                }
                rightCount++;
            }
        }
    }

    /**
     * O(N * log(K))
     */
    public static int findKthLargest_MinHeap(int[] nums, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(k + 1, Integer::compare);
        for (int i = 0; i < nums.length; i++) {
            pq.add(nums[i]);
            if (pq.size() > k)
                pq.poll();
        }
        return pq.poll();
    }

    private static final Random RANDOM = new Random();

    public static int findKthLargest_Quickselect(int[] nums, int k) {
        if (nums.length == 1) {
            return nums[0];
        }
        List<Integer> _nums = new ArrayList<>(nums.length);
        for (Integer num : nums) {
            _nums.add(num);
        }
        return quickselect(_nums, k);
    }

    private static int quickselect(List<Integer> nums, int k) {
        Integer pivot = nums.get(RANDOM.nextInt(nums.size()));
        List<Integer> left = new ArrayList<>();
        List<Integer> mid = new ArrayList<>();
        List<Integer> right = new ArrayList<>();
        for (Integer num : nums) {
            int compare = num.compareTo(pivot);
            if (compare > 0)
                left.add(num);
            else if (compare == 0)
                mid.add(num);
            else
                right.add(num);
        }
        if (left.size() >= k)
            return quickselect(left, k);
        else if (left.size() + mid.size() < k) {
            k = k - left.size() - mid.size();
            return quickselect(right, k);
        } else {
            return pivot;
        }
    }

    public static void main(String[] args) {
        test("BinraryTree", KthLargestElement::findKthLargest_BT);
        test("MinHeap", KthLargestElement::findKthLargest_MinHeap);
        test("Quickselect", KthLargestElement::findKthLargest_Quickselect);
    }

    private static class TestCase {
        private int[] nums;
        private int k;

        TestCase(int[] nums, int k) {
            this.nums = nums;
            this.k = k;
        }

        @Override
        public String toString() {
            return "TestCase [nums=" + Arrays.toString(nums) + ", k=" + k + "]";
        }

    }

    private static void test(String name, BiFunction<int[], Integer, Integer> solution) {
        System.out.println("== " + name + " ==");
        TestCase[] testCases = new TestCase[] {
                new TestCase(new int[] { 3, 2, 1, 5, 6, 4 }, 2),
                new TestCase(new int[] { 3, 2, 3, 1, 2, 4, 5, 5, 6 }, 4),
                new TestCase(new int[] { 7, 6, 5, 4, 3, 2, 1 }, 2),
                new TestCase(new int[] { 5, 2, 4, 1, 3, 6, 0 }, 4),
                new TestCase(new int[] { 99, 99 }, 2) };
        for (TestCase testCase : testCases) {
            System.out.println(testCase);
            System.out.println(solution.apply(testCase.nums, testCase.k));
        }
        System.out.println();
    }

}
