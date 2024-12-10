package meta.medium;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;
import java.util.function.Function;

/**
 * 199. Binary Tree Right Side View
 */
public class BSTRightSideView {

    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public static List<Integer> rightSideView(TreeNode root) {
        List<Integer> rsv = new LinkedList<>();
        if (root == null) {
            return rsv;
        }
        Stack<TreeNode> stack = new Stack<>();
        Stack<TreeNode> stack2 = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            // Add value of the most right node to the result
            TreeNode node = stack.peek();
            rsv.add(node.val);

            // Extract the next level nodes into stack2, from right to left.
            do {
                node = stack.peek();
                if (node.right != null)
                    stack2.add(node.right);
                if (node.left != null)
                    stack2.add(node.left);
                stack.pop();
            } while (!stack.isEmpty());

            // Push next level child into the stack, from left to right.
            while (!stack2.isEmpty()) {
                stack.push(stack2.pop());
            }
        }
        return rsv;
    }

    public static List<Integer> rightSideViewV2(TreeNode root) {
        List<Integer> rsv = new LinkedList<>();
        rightSideView(root, 0, rsv);
        return rsv;
    }

    private static void rightSideView(TreeNode node, int level, List<Integer> rsv) {
        if (node == null)
            return;

        if (rsv.size() == level)
            rsv.add(node.val);

        rightSideView(node.right, level + 1, rsv);
        rightSideView(node.left, level + 1, rsv);
    }

    public static void main(String[] args) {
        test("rightSideView", BSTRightSideView::rightSideView);
        test("rightSideView", BSTRightSideView::rightSideViewV2);
    }

    private static void test(String name, Function<TreeNode, List<Integer>> solution) {
        System.out.println("== " + name + " ==");
        System.out.println(solution.apply(null));
        System.out.println(solution.apply(
                new TreeNode(1, new TreeNode(2, null, new TreeNode(5)), new TreeNode(3, null, new TreeNode(4)))));
        System.out.println(solution.apply(
                new TreeNode(1, new TreeNode(2, new TreeNode(4, new TreeNode(5), null), null), new TreeNode(3))));
    }

}
