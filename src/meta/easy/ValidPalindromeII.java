package meta.easy;

import java.util.function.Function;

public class ValidPalindromeII {

    public static boolean validPalindrome_BrutalForce_StringBuilder(String s) {
        StringBuilder reversed = new StringBuilder(s);
        reversed.reverse();
        if (s.equals(reversed.toString()))
            return true;

        for (int i = 0; i < s.length(); i++) {
            StringBuffer sb = new StringBuffer(s);
            sb.deleteCharAt(i);
            if (sb.toString().equals(sb.reverse().toString())) {
                return true;
            }
        }
        return false;
    }

    public static boolean validPalindrome(String s) {
        for (int i = 0, j = s.length() - 1; i <= j; i++, j--) {
            char left = s.charAt(i);
            char right = s.charAt(j);
            if (left != right) {
                // Skip left char and try if the remaining is palindrome
                boolean palindrome = remaining(s, i + 1, j);

                // Skip left char doesn't work, skip right char and check if the remaining is
                // palindrome
                if (!palindrome)
                    palindrome = remaining(s, i, j - 1);

                return palindrome;
            }
        }
        return true;
    }

    private static boolean remaining(String s, int i, int j) {
        for (; i <= j; i++, j--) {
            char left = s.charAt(i);
            char right = s.charAt(j);
            if (left != right)
                return false;
        }
        return true;
    }

    public static void main(String[] args) {
        test("BrutalForce_StringBuild",
                ValidPalindromeII::validPalindrome_BrutalForce_StringBuilder);
        test("BrutalForce", ValidPalindromeII::validPalindrome);
    }

    private static void test(String name, Function<String, Boolean> solution) {
        System.out.println(String.format("=== %s ===", name));
        System.out.println(solution.apply(
                "aguokepatgbnvfqmgmlcupuufxoohdfpgjdmysgvhmvffcnqxjjxqncffvmhvgsymdjgpfdhooxfuupuculmgmqfvnbgtapekouga"));
        System.out.println(solution.apply("eeccccbebaeeabebccceea"));
        System.out.println(solution.apply("deeee"));
        System.out.println(solution.apply("aba"));
        System.out.println(solution.apply("abca"));
        System.out.println(solution.apply("abc"));
        System.out.println(solution.apply("amanaplanacanalpanama"));
        System.out.println(solution.apply("amanaplanabcanalpanama"));
        System.out.println();
    }

}
