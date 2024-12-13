package meta.medium;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

/**
 * 426. Convert Binary Search Tree to Sorted Doubly Linked List
 */
public class BST2SortedDoublyLinkedList {

    public static void main(String[] args) {
        test("treeToDoublyList", BST2SortedDoublyLinkedList::treeToDoublyList);
    }

    private static void test(String name, Function<Node, Node> solution) {
        System.out.println("== " + name + " ==");
        Node[] test = new Node[] {
                new Node(4, new Node(2, new Node(1), new Node(3)), new Node(5)),
                new Node(30, new Node(13, new Node(-28, new Node(-44, null, new Node(-35)), null), null), null)
        };
        for (int i = 0; i < test.length; i++) {
            Node head = solution.apply(test[i]);
            System.out.print("Test " + i + ": " + head.val + ", ");
            Node node = head.right;
            while (node != null && node != head) {
                System.out.print(node.val + ", ");
                node = node.right;
            }
            System.out.println();
        }
    }

    public static Node treeToDoublyList(Node root) {
        if (root == null) {
            return null;
        }

        List<Node> list = new ArrayList<>();
        treeToList(root, list);
        for (int i = 0; i < list.size() - 1; i++) {
            Node a = list.get(i);
            Node b = list.get(i + 1);
            a.right = b;
            b.left = a;
        }
        Node head = list.get(0);
        Node tail = list.get(list.size() - 1);
        head.left = tail;
        tail.right = head;
        return head;
    }

    private static void treeToList(Node node, List<Node> list) {
        if (node == null) {
            return;
        }
        treeToList(node.left, list);
        list.add(node);
        treeToList(node.right, list);
    }

    private static class Node {
        public int val;
        public Node left;
        public Node right;

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right) {
            val = _val;
            left = _left;
            right = _right;
        }
    };
}
