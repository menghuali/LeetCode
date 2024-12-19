package meta.medium;

import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.Random;

/**
 * 528. Random Pick with Weight
 * 
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(w);
 * int param_1 = obj.pickIndex();
 */
public class RandomPickWithWeight {
    private PriorityQueue<WeighteIndex> weighteIndexs;
    private int total;
    private Random random;

    public RandomPickWithWeight(int[] w) {
        weighteIndexs = new PriorityQueue<>(w.length + 1);
        total = 0;
        for (int i = 0; i < w.length; i++) {
            weighteIndexs.add(new WeighteIndex(total, total + w[i], i));
            total += w[i];
        }
        random = new Random();
    }

    public int pickIndex() {
        int r = random.nextInt(total);
        Iterator<WeighteIndex> iterator = weighteIndexs.iterator();
        while (iterator.hasNext()) {
            WeighteIndex wi = iterator.next();
            if (wi.contains(r)) {
                return wi.index;
            }
        }
        return -1;
    }

    private static class WeighteIndex implements Comparable<WeighteIndex> {
        int lower;
        int upper;
        int index;

        public WeighteIndex(int lower, int upper, int index) {
            this.lower = lower;
            this.upper = upper;
            this.index = index;
        }

        @Override
        public int compareTo(WeighteIndex another) {
            int compare = -Integer.compare(upper - lower, another.upper - another.lower);
            if (compare == 0) {
                compare = Integer.compare(upper, another.upper);
            }
            return compare;
        }

        boolean contains(int number) {
            return lower <= number && number < upper;
        }
    }

    public static void main(String[] args) {
        RandomPickWithWeight random = new RandomPickWithWeight(new int[] { 1, 3 });
        double p25 = 0, p75 = 0;
        for (int i = 0; i < 10000; i++) {
            int index = random.pickIndex();
            if (index == 0)
                p25++;
            else if (index == 1)
                p75++;
            else
                throw new RuntimeException("Incorrect index: " + index);
        }
        System.out.println("p25: " + (p25 / (p25 + p75)) * 100);
        System.out.println("p75: " + (p75 / (p25 + p75)) * 100);
    }
}
