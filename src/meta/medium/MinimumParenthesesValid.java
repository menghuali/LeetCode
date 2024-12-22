package meta.medium;

import java.util.Stack;
import java.util.function.Function;

/**
 * 921. Minimum Add to Make Parentheses Valid
 */
public class MinimumParenthesesValid {

    public static void main(String[] args) {
        test("stack", MinimumParenthesesValid::minAddToMakeValid_Array);
    }

    private static void test(String name, Function<String, Integer> solution) {
        Object[][] test = new Object[][] {
                new Object[] { "())", 1 },
                new Object[] { "(((", 3 }
        };
        for (int i = 0; i < test.length; i++) {
            String s = (String) test[i][0];
            int expect = (int) test[i][1];
            System.out
                    .println(String.format("Test %d: s=%s, expect=%d, actual=%d", i, s, expect, solution.apply(s)));
        }
    }

    public static int minAddToMakeValid_Array(String s) {
        char[] stack = new char[s.length()];
        int size = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (size == 0) {
                stack[size++] = c;
                continue;
            }
            char top = stack[size - 1];
            if (top == '(' && c == ')') {
                size--;
            } else {
                stack[size++] = c;
            }
        }
        return size;
    }

    public static int minAddToMakeValid_Stack(String s) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (stack.isEmpty()) {
                stack.add(c);
                continue;
            }
            char top = stack.peek();
            if (top == '(' && c == ')') {
                stack.pop();
            } else {
                stack.add(c);
            }
        }
        return stack.size();
    }

}
