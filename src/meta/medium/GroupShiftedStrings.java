package meta.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 249. Group Shifted Strings
 */
public class GroupShiftedStrings {

    public static List<List<String>> groupStrings(String[] strings) {
        return new ArrayList<>(
                Arrays.stream(strings).collect(Collectors.groupingBy(s -> toKey(s), Collectors.toList())).values());
    }

    public static List<List<String>> groupStrings2(String[] strings) {
        List<List<String>> groups = new ArrayList<>();
        HashMap<String, List<String>> map = new HashMap<>();
        for (String string : strings) {
            String key = toKey(string);
            List<String> group = null;
            if (map.containsKey(key)) {
                group = map.get(key);
            } else {
                group = new ArrayList<>();
                map.put(key, group);
                groups.add(group);
            }
            group.add(string);
        }
        return groups;
    }

    private static String toKey(String s) {
        if (s.length() == 1)
            return "a";
        if (s.startsWith("a"))
            return s;
        StringBuilder sb = new StringBuilder(s.length());
        char c = 'a';
        sb.append('a');
        char left = s.charAt(0);
        int i = 1;
        while (i < s.length()) {
            char right = s.charAt(i);
            int interval = right - left;
            if (interval < 0) {
                interval += 26;
            }
            c = (char) (c + interval);
            sb.append(c);
            left = right;
            i++;
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        test("default", GroupShiftedStrings::groupStrings);
    }

    private static void test(String name, Function<String[], List<List<String>>> solutoin) {
        String[][] test = new String[][] { new String[] { "abc", "bcd", "acef", "xyz", "az", "ba", "a", "z" },
                new String[] { "a" } };
        System.out.println("== " + name + " ==");
        for (String[] strings : test) {
            System.out.println("Input: " + Arrays.toString(strings));
            System.out.println("Output: " + solutoin.apply(strings));
        }
    }

}
