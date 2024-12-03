package meta.easy;

import java.util.Arrays;
import java.util.function.BiFunction;
import java.util.regex.Pattern;

/**
 * A string can be abbreviated by replacing any number of non-adjacent,
 * non-empty substrings with their lengths. The lengths should not have leading
 * zeros.
 * 
 * For example, a string such as "substitution" could be abbreviated as (but not
 * limited to):
 * 
 * "s10n" ("s ubstitutio n")
 * "sub4u4" ("sub stit u tion")
 * "12" ("substitution")
 * "su3i1u2on" ("su bst i t u ti on")
 * "substitution" (no substrings replaced)
 * The following are not valid abbreviations:
 * 
 * "s55n" ("s ubsti tutio n", the replaced substrings are adjacent)
 * "s010n" (has leading zeros)
 * "s0ubstitution" (replaces an empty substring)
 * Given a string word and an abbreviation abbr, return whether the string
 * matches the given abbreviation.
 * 
 * A substring is a contiguous non-empty sequence of characters within a string.
 * 
 * 
 * 
 * Example 1:
 * 
 * Input: word = "internationalization", abbr = "i12iz4n"
 * Output: true
 * Explanation: The word "internationalization" can be abbreviated as "i12iz4n"
 * ("i nternational iz atio n").
 * Example 2:
 * 
 * Input: word = "apple", abbr = "a2e"
 * Output: false
 * Explanation: The word "apple" cannot be abbreviated as "a2e".
 * 
 * 
 * Constraints:
 * 
 * 1 <= word.length <= 20
 * word consists of only lowercase English letters.
 * 1 <= abbr.length <= 10
 * abbr consists of lowercase English letters and digits.
 * All the integers in abbr will fit in a 32-bit integer.
 */
public class ValidWordAbbreviation {

    public static boolean validWordAbbreviation_Regex(String word, String abbr) {
        // Turn abbr to regex pattern
        // If aphabetic, add to regex
        // If numberic, merge into a temproary buffer, check if the buffer starts with
        // zero, turn number to [a-z]{num}
        StringBuilder regex = new StringBuilder(abbr.length());
        StringBuilder number = new StringBuilder();
        for (int i = 0; i < abbr.length(); i++) {
            char c = abbr.charAt(i);
            if (Character.isAlphabetic(c)) {
                if (number.length() > 0) {
                    regex.append("[a-z]{").append(number.toString()).append("}");
                    number.delete(0, number.length());
                }
                regex.append(c);
            } else {
                if (number.length() == 0 && c == '0') {
                    return false;
                }
                number.append(c);
            }
        }
        if (number.length() > 0) {
            regex.append("[a-z]{").append(number.toString()).append("}");
        }
        Pattern pattern = Pattern.compile(regex.toString());
        return pattern.matcher(word).matches();
    }

    public static boolean validWordAbbreviation_Split(String word, String abbr) {
        String[] numbers = abbr.split("[a-z]+");
        if (numbers.length > 0 && numbers[0].isEmpty())
            numbers = Arrays.copyOfRange(numbers, 1, numbers.length);
        for (String str : numbers) {
            if (str.startsWith("0"))
                return false;
        }
        String[] letters = abbr.split("\\d+");
        if (letters.length > 0 && letters[0].isEmpty())
            letters = Arrays.copyOfRange(letters, 1, letters.length);

        String[] a, b;
        if (letters.length > 0 && word.startsWith(letters[0])) {
            a = letters;
            b = numbers;
        } else {
            a = numbers;
            b = letters;
        }

        int i = 0, j = 0, k = 0;
        String[] abbrs = new String[letters.length + numbers.length];

        for (; i < a.length && j < b.length; i++, j++) {
            abbrs[k++] = a[i];
            abbrs[k++] = b[j];
        }

        if (i < a.length)
            abbrs[k++] = a[i];
        if (j < b.length)
            abbrs[k++] = b[j];

        int q = 0;
        for (String str : abbrs) {
            if (Character.isDigit(str.charAt(0)))
                q += Integer.parseInt(str);
            else if (!word.startsWith(str, q))
                return false;
            else
                q += str.length();

        }
        return q == word.length();
    }

    public static boolean validWordAbbreviation(String word, String abbr) {
        int i = 0, j = 0;
        for (; i < word.length() && j < abbr.length();) {
            char c = abbr.charAt(j);
            if (Character.isDigit(c)) {
                int k = retrieveNumber(abbr, j);
                String num = abbr.substring(j, k);
                if (num.startsWith("0"))
                    return false;
                j = k;
                i += Integer.parseInt(num);
            } else {
                if (word.charAt(i) == abbr.charAt(j)) {
                    i++;
                    j++;
                } else
                    return false;
            }
        }
        return i == word.length() && j == abbr.length();
    }

    private static int retrieveNumber(String abbr, int offset) {
        for (; offset < abbr.length(); offset++) {
            char c = abbr.charAt(offset);
            if (!Character.isDigit(c))
                break;
        }
        return offset;
    }

    public static void main(String[] args) {
        // test("validWordAbbreviation_Regex",
        //         ValidWordAbbreviation::validWordAbbreviation_Regex);
        // test("validWordAbbreviation_Split", ValidWordAbbreviation::validWordAbbreviation_Split);
        test("retrieveNumber", ValidWordAbbreviation::validWordAbbreviation);
    }

    private static void test(String name, BiFunction<String, String, Boolean> solution) {
        System.out.println(String.format("== %s ==", name));
        System.out.println(solution.apply("substitution", "s10n"));
        System.out.println(solution.apply("substitution", "sub4u4"));
        System.out.println(solution.apply("substitution", "12"));
        System.out.println(solution.apply("substitution", "substitution"));
        System.out.println(solution.apply("substitution", "s55n"));
        System.out.println(solution.apply("substitution", "s010n"));
        System.out.println(solution.apply("substitution", "s0ubstitution"));
        System.out.println(solution.apply("internationalization", "i18n"));
        System.out.println(solution.apply("internationalization", "i12iz4n"));
        System.out.println(solution.apply("apple", "a2e"));
        System.out.println(solution.apply("a", "2"));
        System.out.println(solution.apply("hi", "1i"));
    }

}
