package meta.medium;

/**
 * 708. Insert into a Sorted Circular Linked List
 */
public class InsertSortedCircularLinkedList {

    public Node insert(Node head, int insertVal) {
        Node insertNode = new Node(insertVal);
        if (head == null) {
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
            if (current.val <= insertVal && insertVal <= next.val) {
                current.next = insertNode;
                insertNode.next = next;
                return head;
            }
            current = current.next;
            next = next.next;
        }
        current.next = insertNode;
        insertNode.next = next;
        return head;
    }

    class Node {
        public int val;
        public Node next;
    
        public Node() {}
    
        public Node(int _val) {
            val = _val;
        }
    
        public Node(int _val, Node _next) {
            val = _val;
            next = _next;
        }
    };

}
