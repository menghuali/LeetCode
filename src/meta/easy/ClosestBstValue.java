package meta.easy;

/**
 * Given the root of a binary search tree and a target value, return the value
 * in the BST that is closest to the target. If there are multiple answers,
 * print the smallest.
 * 
 * 
 * Example 1:
 * Input: root = [4,2,5,1,3], target = 3.714286
 * Output: 4
 * 
 * Example 2:
 * Input: root = [1], target = 4.428571
 * Output: 1
 * 
 * Constraints:
 * The number of nodes in the tree is in the range [1, 104].
 * 0 <= Node.val <= 109
 * -109 <= target <= 109
 */
public class ClosestBstValue {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public static int closestValue(TreeNode root, double target) {
        TreeNode node = root;
        int closest = node.val;
        double minDelta = 218;

        while (node != null) {
            double delta = Math.abs(target - node.val);
            if (delta < minDelta) {
                closest = node.val;
                minDelta = delta;
            } else if (delta == minDelta) {
                closest = Math.min(closest, node.val);
            }
            node = target < node.val ? node.left : node.right;
        }
        return closest;
    }

}
