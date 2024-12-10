package meta.medium;

import java.util.Stack;

/**
 * 236. Lowest Common Ancestor of a Binary Tree
 */
public class LowestCommonAncestorBT {
    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }

        public TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public static TreeNode lowestCommonAncestor_StackRecursive(TreeNode root, TreeNode p, TreeNode q) {
        Stack<TreeNode> pAncestors = new Stack<>();
        ancestors(root, p, pAncestors);
        Stack<TreeNode> qAncestors = new Stack<>();
        ancestors(root, q, qAncestors);
        TreeNode common = null;
        TreeNode pAncestor = pAncestors.pop();
        TreeNode qAncestor = qAncestors.pop();
        while (pAncestor == qAncestor) {
            common = pAncestor;
            if (pAncestors.isEmpty() || qAncestors.isEmpty())
                break;
            pAncestor = pAncestors.pop();
            qAncestor = qAncestors.pop();
        }
        return common;
    }

    private static void ancestors(TreeNode current, TreeNode node, Stack<TreeNode> ancestors) {
        if (current == null) {
            return;
        }
        if (current.val == node.val) {
            ancestors.push(current);
            return;
        }
        ancestors(current.left, node, ancestors);
        if (ancestors.isEmpty()) {
            ancestors(current.right, node, ancestors);
        }
        if (!ancestors.isEmpty())
            ancestors.push(current);
    }

    public static void main(String[] args) {
        TreeNode[][] tests = new TreeNode[][] {
                new TreeNode[] { new TreeNode(3,
                        new TreeNode(5, new TreeNode(6), new TreeNode(2, new TreeNode(7), new TreeNode(4))),
                        new TreeNode(1, new TreeNode(0), new TreeNode(8))), new TreeNode(5), new TreeNode(1) },
                new TreeNode[] { new TreeNode(3,
                        new TreeNode(5, new TreeNode(6), new TreeNode(2, new TreeNode(7), new TreeNode(4))),
                        new TreeNode(1, new TreeNode(0), new TreeNode(8))), new TreeNode(6), new TreeNode(4) },
                new TreeNode[] { new TreeNode(3,
                        new TreeNode(5, new TreeNode(6), new TreeNode(2, new TreeNode(7), new TreeNode(4))),
                        new TreeNode(1, new TreeNode(0), new TreeNode(8))), new TreeNode(7), new TreeNode(6) },
                new TreeNode[] { new TreeNode(3,
                        new TreeNode(5, new TreeNode(6), new TreeNode(2, new TreeNode(7), new TreeNode(4))),
                        new TreeNode(1, new TreeNode(0), new TreeNode(8))), new TreeNode(6), new TreeNode(5) },
                new TreeNode[] { new TreeNode(3,
                        new TreeNode(5, new TreeNode(6), new TreeNode(2, new TreeNode(7), new TreeNode(4))),
                        new TreeNode(1, new TreeNode(0), new TreeNode(8))), new TreeNode(1), new TreeNode(2) },
                new TreeNode[] { new TreeNode(3,
                        new TreeNode(5, new TreeNode(6), new TreeNode(2, new TreeNode(7), new TreeNode(4))),
                        new TreeNode(1, new TreeNode(0), new TreeNode(8))), new TreeNode(5), new TreeNode(4) },
                new TreeNode[] {
                        new TreeNode(1, new TreeNode(2), null), new TreeNode(1), new TreeNode(2)
                }
        };
        for (int i = 0; i < tests.length; i++) {
            System.out.println(
                    "Case 1: " + lowestCommonAncestor_StackRecursive(tests[i][0], tests[i][1], tests[i][2]).val);
        }
    }

}
