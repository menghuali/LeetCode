package meta.medium;

import java.util.HashMap;
import java.util.Map;

/**
 * 791. Custom Sort String
 */
public class CustomSortString {

    public static void main(String[] args) {
        String[][] test = new String[][] {
                new String[] { "cba", "abcd" },
                new String[] { "bcafg", "abcd" },
                new String[] { "abcdef", "cbacbacbazgfedcba" }
        };
        for (int i = 0; i < test.length; i++) {
            String order = test[i][0];
            String s = test[i][1];
            System.out.println("Test 1: order=" + order + ", s=" + s + ", permutation=" + customSortString(order, s));
        }
    }

    public static String customSortString(String order, String s) {
        Map<Character, Count> map = new HashMap<>(order.length());
        for (int i = 0; i < order.length(); i++) {
            map.put(order.charAt(i), new Count());
        }

        StringBuilder permuted = new StringBuilder(s.length());
        StringBuilder tail = new StringBuilder(s.length());
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            Count count = map.get(c);
            if (count != null) {
                count.val++;
            } else {
                tail.append(c);
            }
        }
        for (int i = 0; i < order.length(); i++) {
            char c = order.charAt(i);
            Count count = map.get(c);
            for (int j = 0; j < count.val; j++) {
                permuted.append(c);
            }
        }
        permuted.append(tail.toString());
        return permuted.toString();
    }

    private static class Count {
        int val = 0;
    }

}
