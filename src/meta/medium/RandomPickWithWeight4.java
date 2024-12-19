package meta.medium;

public class RandomPickWithWeight4 {
    private int[] prefixSums;
    private int sum;

    public RandomPickWithWeight4(int[] w) {
        prefixSums = new int[w.length];
        sum = 0;
        for (int i = 0; i < w.length; i++) {
            sum += w[i];
            prefixSums[i] = sum;
        }
    }

    public int pickIndex() {
        if (prefixSums.length == 1)
            return 0;

        double target = this.sum * Math.random();

        // run a binary search to find the target zone
        int low = 0, high = this.prefixSums.length;
        while (low < high) {
            // better to avoid the overflow
            int mid = low + (high - low) / 2;
            if (target > this.prefixSums[mid])
                low = mid + 1;
            else
                high = mid;
        }
        return low;
    }

    public static void main(String[] args) {
        RandomPickWithWeight4 random = new RandomPickWithWeight4(new int[] { 1, 3 });
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
