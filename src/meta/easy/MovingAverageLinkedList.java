package meta.easy;

import java.util.LinkedList;

public class MovingAverageLinkedList {

    private LinkedList<Integer> queue;
    private int size;
    private int count;
    private double sum;

    public MovingAverageLinkedList(int size) {
        queue = new LinkedList<>();
        this.size = size;
        count = 0;
        sum = 0;
    }

    public double next(int val) {
        if (count < size) {
            queue.add(val);
            sum += val;
            count++;
            return sum / count;
        } else {
            int exitWin = queue.poll();
            queue.add(val);
            sum = sum - exitWin + val;
            return sum / size;
        }
    }

    public static void main(String[] args) {
        MovingAverageLinkedList ma = new MovingAverageLinkedList(3);
        System.err.println(ma.next(1));
        System.err.println(ma.next(10));
        System.err.println(ma.next(3));
        System.err.println(ma.next(5));
        System.err.println(ma.next(3));
        System.err.println(ma.next(3));
        System.err.println(ma.next(3));
        System.err.println(ma.next(12));
    }

}
