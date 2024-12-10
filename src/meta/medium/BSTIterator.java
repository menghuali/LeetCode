package meta.medium;

import java.util.Stack;

/**
 * 173. Binary Search Tree Iterator
 */
public class BSTIterator {

    @SuppressWarnings("unused")
    private class TreeNode {
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

    private Stack<TreeNode> stack;

    public BSTIterator(TreeNode root) {
        stack = new Stack<>();
        push(root);
    }

    private void push(TreeNode node) {
        while (node != null) {
            stack.push(node);
            node = node.left;
        }
    }

    /**
     * Moves the pointer to the right, then returns the number at the pointer.
     * 
     * @return the number at the pointer
     */
    public int next() {
        TreeNode node = stack.pop();
        push(node.right);
        return node.val;
    }

    /**
     * @return Returns true if there exists a number in the traversal to the right
     *         of the pointer, otherwise returns false.
     */
    public boolean hasNext() {
        return !stack.isEmpty();
    }

}
