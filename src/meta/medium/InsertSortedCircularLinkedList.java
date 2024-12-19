package meta.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * 708. Insert into a Sorted Circular Linked List
 */
public class InsertSortedCircularLinkedList {

    public static void main(String[] args) {
        Object[][] test = new Object[][] {
                new Object[] {
                        new int[] { 1, 3, 4 },
                        new int[] { 1, 2 }
                },
                new Object[] {
                        new int[] { 1 },
                        new int[] { 0, 0 }
                },
                new Object[] {
                        new int[0],
                        new int[] { -1, 0 }
                },
                new Object[] {
                        new int[] { 10, 20, 30, 40 },
                        new int[] { 2, 50 }
                },
                new Object[] {
                        new int[] { 10, 20, 30, 40 },
                        new int[] { 2, 5 }
                },
                new Object[] {
                        new int[] { 10, 20, 30, 40 },
                        new int[] { 2, 15 }
                },
                new Object[] {
                        new int[] { 10, 20, 30, 40 },
                        new int[] { 2, 35 }
                },
                new Object[] {
                        new int[] { 1, 3, 3, 3 },
                        new int[] { 2, 4 }
                },
                new Object[] {
                        new int[] { 3, 3, 3, 3 },
                        new int[] { 2, 2 }
                },
                new Object[] {
                        new int[] { 3, 3, 3, 3 },
                        new int[] { 2, 4 }
                }
        };
        for (int i = 0; i < test.length; i++) {
            int[] nums = (int[]) test[i][0];
            int headIndex = ((int[]) test[i][1])[0];
            int insertVal = ((int[]) test[i][1])[1];
            Node head = null;
            if (nums.length > 0) {
                Node first = new Node(nums[0]);
                if (headIndex == 0) {
                    head = first;
                }
                Node current = first;
                Node next = null;
                for (int j = 1; j < nums.length; j++) {
                    if (headIndex == j - 1) {
                        head = current;
                    }
                    next = new Node(nums[j]);
                    current.next = next;
                    current = next;
                }
                current.next = first;
            } else {

            }
            System.out.println("Input " + i + ": headIndex=" + headIndex + ", head=" + (head == null ? null : head.val)
                    + ", insertVal=" + insertVal + ", lists=" + (head == null ? "[]" : toList(head)));
            System.out.println("Output: " + toList(insert(head, insertVal)));
        }
    }

    private static List<Integer> toList(Node head) {
        List<Integer> array = new ArrayList<>();
        Node node = head;
        do {
            array.add(node.val);
            node = node.next;
        } while (node != head);
        return array;
    }

    public static Node insert(Node head, int insertVal) {
        Node insertNode = new Node(insertVal);
        if (head == null) {
            insertNode.next = insertNode;
            return insertNode;
        }
        if (head.next == head || head.next == null) {
            head.next = insertNode;
            insertNode.next = head;
            return head;
        }
        Node current = head;
        Node next = current.next;
        while (next != head) {
            if (insertVal >= current.val && insertVal <= next.val) {
                break;
            }
            if (insertVal > current.val && current.val > next.val) {
                break;
            }
            if (insertVal < current.val && insertVal < next.val && current.val > next.val) {
                break;
            }
            current = next;
            next = current.next;
        }
        current.next = insertNode;
        insertNode.next = next;
        return head;
    }

    private static class Node {
        public int val;
        public Node next;

        public Node(int _val) {
            val = _val;
        }
    };

}
