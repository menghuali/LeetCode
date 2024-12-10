package sandbox;

import java.util.PriorityQueue;
import java.util.Random;

public class PriorityQueueDemo {

    public static void main(String[] args) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(Integer::compare);
        Random random = new Random();
        for (int i = 0; i < 100; i++) {
            int num = random.nextInt(1000);
            System.out.print(num + ", ");
            pq.add(num);
        }

        System.out.println("\n\nPoll from PriorityQueue");
        Integer num = pq.poll();
        while (num != null) {
            System.out.print(num + ", ");
            num = pq.poll();
        }
    }

}
