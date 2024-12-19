package meta.medium;

import java.util.Random;

/**
 * 528. Random Pick with Weight
 * 
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(w);
 * int param_1 = obj.pickIndex();
 */
public class RandomPickWithWeight2 {
    private int[] prefixSums;
    private int sum;
    private Random random;

    public RandomPickWithWeight2(int[] w) {
        prefixSums = new int[w.length];
        sum = 0;
        for (int i = 0; i < w.length; i++) {
            sum += w[i];
            prefixSums[i] = sum;
        }
        random = new Random();
    }

    public int pickIndex() {
        double pick = random.nextDouble() * sum;
        for (int i = 0; i < prefixSums.length; i++) {
            if (pick < prefixSums[i]) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        RandomPickWithWeight2 random = new RandomPickWithWeight2(new int[] { 1, 3 });
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
