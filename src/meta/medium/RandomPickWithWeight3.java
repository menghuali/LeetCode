package meta.medium;

import java.util.Random;

public class RandomPickWithWeight3 {
    private int[] prefixSums;
    private int sum;
    private Random random;

    public RandomPickWithWeight3(int[] w) {
        prefixSums = new int[w.length];
        sum = 0;
        for (int i = 0; i < w.length; i++) {
            sum += w[i];
            prefixSums[i] = sum;
        }
        random = new Random();
    }

    public int pickIndex() {
        if (prefixSums.length == 1) {
            return 0;
        }
        double pick = random.nextDouble() * sum;
        return pickIndex(pick, 0, prefixSums.length);
    }

    private int pickIndex(double pick, int begin, int end) {
        if (end - begin == 1) {
            return begin;
        }

        int mid = (end + begin) / 2;
        if (pick > prefixSums[mid]) {
            return pickIndex(pick, mid + 1, end);
        }

        if (pick < prefixSums[mid] && pick > prefixSums[mid - 1]) {
            return mid;
        }

        return pickIndex(pick, begin, mid);
    }

    public static void main(String[] args) {
        RandomPickWithWeight3 random = new RandomPickWithWeight3(new int[] { 1, 3 });
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
