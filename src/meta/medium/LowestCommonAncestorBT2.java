package meta.medium;

import java.util.ArrayList;
import java.util.List;

public class LowestCommonAncestorBT2 {
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
        List<TreeNode> pAncestors = new ArrayList<>();
        ancestors(root, p, pAncestors);
        for (int i = 0; i < pAncestors.size(); i++) {
            TreeNode current = pAncestors.get(i);
            if (findDescendant(current, q))
                return current;
        }
        return root;
    }

    private static boolean findDescendant(TreeNode current, TreeNode descendant) {
        if (current == null)
            return false;
        if (current.val == descendant.val) {
            return true;
        }
        return findDescendant(current.left, descendant) || findDescendant(current.right, descendant);
    }

    private static void ancestors(TreeNode current, TreeNode node, List<TreeNode> ancestors) {
        if (current == null) {
            return;
        }
        if (current.val == node.val) {
            ancestors.add(current);
            return;
        }
        ancestors(current.left, node, ancestors);
        if (ancestors.isEmpty()) {
            ancestors(current.right, node, ancestors);
        }
        if (!ancestors.isEmpty())
            ancestors.add(current);
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
