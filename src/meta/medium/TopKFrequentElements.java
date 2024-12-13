package meta.medium;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.BiFunction;

/**
 * 347. Top K Frequent Elements
 */
public class TopKFrequentElements {
    public static void main(String[] args) {
        test("default", TopKFrequentElements::topKFrequent);
    }

    private static void test(String name, BiFunction<int[], Integer, int[]> solution) {
        System.out.println("== " + name + " ==");
        Map<Integer, int[]> test = new HashMap<>();
        test.put(2, new int[] { 1, 1, 1, 2, 2, 3 });
        test.put(1, new int[] { 1 });
        for (Entry<Integer, int[]> e : test.entrySet()) {
            System.out.println(String.format("nums=%s, k=%d : %s", Arrays.toString(e.getValue()), e.getKey(),
                    Arrays.toString(solution.apply(e.getValue(), e.getKey()))));
        }
    }

    public static int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Counter> map = new HashMap<>();
        for (int i : nums) {
            Counter counter = null;
            if (map.containsKey(i)) {
                counter = map.get(i);
            } else {
                counter = new Counter(i);
                map.put(i, counter);
            }
            counter.count++;
        }
        Counter[] counters = new Counter[map.values().size()];
        map.values().toArray(counters);
        Arrays.sort(counters, (x, y) -> Integer.compare(x.count, y.count));
        int[] topKFreqElm = new int[k];
        for (int i = 0; i < k; i++) {
            topKFreqElm[i] = counters[counters.length - i - 1].num;
        }
        return topKFreqElm;
    }

    private static class Counter {
        int count = 0;
        int num = 0;

        Counter(int num) {
            this.num = num;
        }
    }

}
