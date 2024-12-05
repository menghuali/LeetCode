package meta.medium;

import java.util.HashMap;
import java.util.Map;

public class CopyListWithRandomPointer {
    private static class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }

    public static Node copyRandomList(Node head) {
        if (head == null)
            return null;
        Map<Node, Node> map = new HashMap<>();
        Node headCopy = copy(head);
        map.put(head, headCopy);
        Node origin = head;
        Node copy = headCopy;
        while (origin.next != null) {
            origin = origin.next;
            copy.next = copy(origin);
            copy = copy.next;
            map.put(origin, copy);
        }

        copy = headCopy;
        do {
            if (copy.random != null) {
                copy.random = map.get(copy.random);
            }
            copy = copy.next;
        } while (copy != null);
        return headCopy;
    }

    private static Node copy(Node origin) {
        Node copy = new Node(origin.val);
        copy.random = origin.random;
        return copy;
    }

}
