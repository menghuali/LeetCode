package meta.easy;

/**
 * A phrase is a palindrome if, after converting all uppercase letters into
 * lowercase letters and removing all non-alphanumeric characters, it reads the
 * same forward and backward. Alphanumeric characters include letters and
 * numbers.
 * 
 * Given a string s, return true if it is a palindrome, or false otherwise.
 * 
 * 
 * Example 1:
 * 
 * Input: s = "A man, a plan, a canal: Panama"
 * Output: true
 * Explanation: "amanaplanacanalpanama" is a palindrome.
 * Example 2:
 * 
 * Input: s = "race a car"
 * Output: false
 * Explanation: "raceacar" is not a palindrome.
 * Example 3:
 * 
 * Input: s = " "
 * Output: true
 * Explanation: s is an empty string "" after removing non-alphanumeric
 * characters.
 * Since an empty string reads the same forward and backward, it is a
 * palindrome.
 * 
 * 
 * Constraints:
 * 
 * 1 <= s.length <= 2 * 105
 * s consists only of printable ASCII characters.
 */
public class ValidPalindrome {

    public boolean isPalindrome_1(String s) {
        // 1. Remove non-Alphanumeric characters
        // 2. Convert al characters to lower case.
        // 3. Reverse the character order.
        // 4. Compare the reversed string with the un-reversed string.

        char[] chars = new char[s.length()];
        s.getChars(0, s.length(), chars, 0);

        StringBuilder sb = new StringBuilder(chars.length);
        for (char c : chars) {
            if ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || (c >= '0' && c <= '9')) {
                sb.append(Character.toLowerCase(c));
            }
        }
        String original = sb.toString();
        String reversed = sb.reverse().toString();
        return original.equals(reversed);
    }

    public boolean isPalindrome_2(String s) {
        // 1. Convert string to lowercase
        // 2. Convert lowercase string to char array
        // 3. Compare char array element i with element n - i. It is not Palindrome if
        // an umatching is found.
        String lowercases = s.toLowerCase();
        char[] chars = new char[lowercases.length()];
        lowercases.getChars(0, lowercases.length(), chars, 0);
        for (int left = 0, right = chars.length - 1; left < right;) {
            char lc = chars[left];
            if (!Character.isLetterOrDigit(lc)) {
                left++;
                continue;
            }
            char rc = chars[right];
            if (!Character.isLetterOrDigit(rc)) {
                right--;
                continue;
            }
            if (lc != rc) {
                return false;
            } else {
                left++;
                right--;
            }
        }
        return true;
    }

    public boolean isPalindrome_3(String s) {
        for (int left = 0, right = s.length() - 1; left < right;) {
            // Get left char
            char lc = s.charAt(left);

            // Ingore left char if it's not alphanumeric
            if (!Character.isLetterOrDigit(lc)) {
                left++;
                continue;
            }

            // Same to right letter
            char rc = s.charAt(right);
            if (!Character.isLetterOrDigit(rc)) {
                right--;
                continue;
            }

            if (Character.toLowerCase(lc) != Character.toLowerCase(rc)) {
                return false; // Not Palindrome. Return.
            } else {
                // Left equals right. Move left to right, and right to left.
                left++;
                right--;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        ValidPalindrome solution = new ValidPalindrome();
        System.out.println(
                "A man, a plan, a canal: Panama : " + solution.isPalindrome_3("A man, a plan, a canal: Panama"));
        System.out.println("race a car : " + solution.isPalindrome_3("race a car"));
        System.out.println(" " + solution.isPalindrome_3(" "));
    }

}
