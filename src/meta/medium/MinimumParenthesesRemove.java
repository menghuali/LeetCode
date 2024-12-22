package meta.medium;

/**
 * 1249. Minimum Remove to Make Valid Parentheses
 */
public class MinimumParenthesesRemove {

    public static void main(String[] args) {
        String[] test = new String[] {
                "lee(t(c)o)de)", "a)b(c)d", "))(("
        };
        for (int i = 0; i < test.length; i++) {
            System.out.println(String.format("Test %d: s=%s, output=%s", i, test[i], minRemoveToMakeValid(test[i])));
        }
    }

    public static String minRemoveToMakeValid(String s) {
        int[] leftParenthese = new int[s.length()];
        int leftParentheseSize = 0;
        char[] parenthesesValid = new char[s.length()];
        int size = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == ')') {
                if (leftParentheseSize > 0) {
                    parenthesesValid[size] = c;
                    size++;
                    leftParentheseSize--;
                }
            } else {
                parenthesesValid[size] = c;
                if (c == '(') {
                    leftParenthese[leftParentheseSize++] = size;
                }
                size++;
            }
        }
        for (int i = 0; i < leftParentheseSize; i++) {
            parenthesesValid[leftParenthese[i]] = '0';
        }
        StringBuilder sb = new StringBuilder(size - leftParentheseSize);
        for (int i = 0; i < size; i++) {
            if (parenthesesValid[i] != '0') {
                sb.append(parenthesesValid[i]);
            }
        }
        return sb.toString();
    }

}
