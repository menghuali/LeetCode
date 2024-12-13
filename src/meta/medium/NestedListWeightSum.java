package meta.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

/**
 * 339. Nested List Weight Sum
 */
public class NestedListWeightSum {

    public static int depthSum(List<NestedInteger> nestedList) {
        return depthSum(nestedList, 1);
    }

    private static int depthSum(List<NestedInteger> nestedList, int depth) {
        int sum = 0;
        for (NestedInteger no : nestedList) {
            if (no.isInteger())
                sum += no.getInteger() * depth;
            else
                sum += depthSum(no.getList(), depth + 1);
        }
        return sum;
    }

    public static void main(String[] args) {
        test("first", NestedListWeightSum::depthSum);
    }

    private static void test(String name, Function<List<NestedInteger>, Integer> solution) {
        System.out.println("== " + name + " ==");
        List<List<NestedInteger>> test = new ArrayList<>();
        test.add(Arrays.asList(new NestedInteger[] {
                new NestedInteger(Arrays.asList(new NestedInteger(1), new NestedInteger(1))),
                new NestedInteger(2),
                new NestedInteger(Arrays.asList(new NestedInteger(1), new NestedInteger(1))) }));
        test.add(Arrays.asList(new NestedInteger[] {
                new NestedInteger(1),
                new NestedInteger(Arrays.asList(new NestedInteger[] {
                        new NestedInteger(4),
                        new NestedInteger(Arrays.asList(new NestedInteger[] { new NestedInteger(6) }))
                }))
        }));
        for (int i = 0; i < test.size(); i++) {
            System.out.println("Test 1: " + solution.apply(test.get(i)));
        }
    }

    private static class NestedInteger {
        int val;
        private List<NestedInteger> list;

        NestedInteger(int val) {
            this.val = val;
        }

        NestedInteger(List<NestedInteger> list) {
            this.list = list;
        }

        boolean isInteger() {
            return list == null;
        }

        Integer getInteger() {
            return val;
        }

        List<NestedInteger> getList() {
            return list;
        }
    }

}
