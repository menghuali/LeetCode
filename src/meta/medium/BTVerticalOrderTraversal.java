package meta.medium;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class BTVerticalOrderTraversal {

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

    public static List<List<Integer>> verticalOrder(TreeNode root) {
        if (root == null)
            return new ArrayList<>();
        ArrayList<List<Integer>> columns = new ArrayList<>();
        List<Integer> indexes = new ArrayList<>();
        indexes.add(-1);
        List<TreeNode> nodes = new ArrayList<>();
        nodes.add(root);
        verticalOrder(nodes, columns, indexes);
        return columns;
    }

    private static void verticalOrder(List<TreeNode> nodes, List<List<Integer>> columns, List<Integer> indexes) {
        if (nodes.isEmpty()) {
            return;
        }
        int adjust = 0;
        List<TreeNode> childNodes = new ArrayList<>();
        List<Integer> childIndexes = new ArrayList<>();
        for (int i = 0; i < nodes.size(); i++) {
            TreeNode node = nodes.get(i);
            int index = indexes.get(i);
            if (index + adjust == -1) {
                List<Integer> col = new ArrayList<>();
                columns.add(0, col);
                adjust++;
            }
            if (index + adjust >= columns.size()) {
                List<Integer> col = new ArrayList<>();
                columns.add(col);
            }
            List<Integer> col = columns.get(index + adjust);
            col.add(node.val);
            if (node.left != null) {
                childNodes.add(node.left);
                childIndexes.add(index + adjust - 1);
            }
            if (node.right != null) {
                childNodes.add(node.right);
                childIndexes.add(index + adjust + 1);
            }
        }
        verticalOrder(childNodes, columns, childIndexes);
    }

    public static void main(String[] args) {
        test("first", BTVerticalOrderTraversal::verticalOrder);
    }

    private static void test(String name, Function<TreeNode, List<List<Integer>>> solution) {
        TreeNode[] tests = new TreeNode[] {
                new TreeNode(3, new TreeNode(9), new TreeNode(20, new TreeNode(15), new TreeNode(7))),
                new TreeNode(3, new TreeNode(9, new TreeNode(4), new TreeNode(0)),
                        new TreeNode(8, new TreeNode(1), new TreeNode(7))),
                new TreeNode(1,
                        new TreeNode(2, new TreeNode(4, null, new TreeNode(5, null, new TreeNode(6))),
                                new TreeNode(10)),
                        new TreeNode(3, new TreeNode(9), new TreeNode(11))),
                null
        };
        System.out.println("== " + name + " ==");
        for (int i = 0; i < tests.length; i++) {
            System.out.println("Case " + i + ": " + solution.apply(tests[i]));
        }

    }

}
