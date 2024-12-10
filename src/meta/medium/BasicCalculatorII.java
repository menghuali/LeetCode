package meta.medium;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.function.Function;

/**
 * 227. Basic Calculator II
 */
public class BasicCalculatorII {

    public static int calculate_Regex(String s) {
        String[] subExps = s.trim().split("\\+");
        int result = 0;
        for (String expr : subExps) {
            result += subtract(expr);
        }
        return result;
    }

    private static int subtract(String exp) {
        String[] subExp = exp.trim().split("-");
        int result = mutiplyAndDivide(subExp[0]);
        for (int i = 1; i < subExp.length; i++) {
            result -= mutiplyAndDivide(subExp[i]);
        }
        return result;
    }

    private static int mutiplyAndDivide(String exp) {
        String[] nums = exp.trim().split("\\*|/");
        String[] operator = exp.split("[0-9]+");
        int result = Integer.parseInt(nums[0].trim());
        int i = 1;
        int j = 0;
        while (j < operator.length) {
            if (operator[j].isEmpty()) {
                j++;
                continue;
            } else if (operator[i].equals("*"))
                result *= Integer.parseInt(nums[i].trim());
            else
                result /= Integer.parseInt(nums[i].trim());
            j++;
            i++;
        }
        return result;
    }

    private static class Offset {
        int index = 0;
    }

    public static int calculate_Stack(String s) {
        Stack<Integer> numbers = new Stack<>();
        Stack<Character> operators = new Stack<>();
        Offset offset = new Offset();
        numbers.push(nextNumber(s, offset));
        Character op = nextOperator(s, offset);
        while (op != null) {
            int right = nextNumber(s, offset);
            if (op == '*' || op == '/') {
                int left = numbers.pop();
                if (op == '*')
                    numbers.push(left * right);
                else
                    numbers.push(left / right);
            } else {
                numbers.push(right);
                operators.push(op);
            }
            op = nextOperator(s, offset);
        }
        int result = numbers.get(0);
        for (int i = 0; i < operators.size(); i++) {
            op = operators.get(i);
            if (op == '+')
                result += numbers.get(i + 1);
            else
                result -= numbers.get(i + 1);
        }
        return result;
    }

    private static Character nextOperator(String s, Offset offset) {
        while (offset.index < s.length()) {
            char c = s.charAt(offset.index++);
            if (c == '+' || c == '-' || c == '*' || c == '/')
                return c;
        }
        return null;
    }

    private static int nextNumber(String s, Offset offset) {
        StringBuilder sb = new StringBuilder();
        while (offset.index < s.length()) {
            char c = s.charAt(offset.index++);
            if (c == ' ')
                continue;
            if (Character.isDigit(c))
                sb.append(c);
            else {
                offset.index--;
                break;
            }
        }
        return Integer.parseInt(sb.toString());
    }

    public static int calculate_Stack2(String s) {
        Stack<Character> ops = new Stack<>();
        Stack<Integer> numbers = new Stack<>();

        int num = 0;
        for (int i = 0; i < s.length();) {
            char c = s.charAt(i++);
            if (c == ' ') {
                continue;
            }
            if (Character.isDigit(c)) {
                num = num * 10 + Character.getNumericValue(c);
                if (i == s.length() || !Character.isDigit(s.charAt(i))) {
                    if (!ops.isEmpty()) {
                        char op = ops.peek();
                        if (op == '*') {
                            num = numbers.pop() * num;
                            ops.pop();
                        } else if (op == '/') {
                            num = numbers.pop() / num;
                            ops.pop();
                        }
                    }
                    numbers.push(num);
                    num = 0;
                }
            } else {
                ops.push(c);
            }
        }
        int result = numbers.get(0);
        for (int i = 0; i < ops.size(); i++) {
            char op = ops.get(i);
            if (op == '+')
                result += numbers.get(i + 1);
            else
                result -= numbers.get(i + 1);
        }
        return result;
    }

    public static int calculate_ArrayList(String s) {
        List<Character> ops = new ArrayList<>();
        List<Integer> numbers = new ArrayList<>();

        int num = 0;
        for (int i = 0; i < s.length();) {
            char c = s.charAt(i++);
            if (c == ' ') {
                continue;
            }
            if (Character.isDigit(c)) {
                num = num * 10 + Character.getNumericValue(c);
                if (i == s.length() || !Character.isDigit(s.charAt(i))) {
                    if (!ops.isEmpty()) {
                        char op = ops.get(ops.size() - 1);
                        if (op == '*') {
                            num = numbers.remove(numbers.size() - 1) * num;
                            ops.remove(ops.size() - 1);
                        } else if (op == '/') {
                            num = numbers.remove(numbers.size() - 1) / num;
                            ops.remove(ops.size() - 1);
                        }
                    }
                    numbers.add(num);
                    num = 0;
                }
            } else {
                ops.add(c);
            }
        }
        int result = numbers.get(0);
        for (int i = 0; i < ops.size(); i++) {
            char op = ops.get(i);
            if (op == '+')
                result += numbers.get(i + 1);
            else
                result -= numbers.get(i + 1);
        }
        return result;
    }

    public static void main(String[] args) {
        test("Regex", BasicCalculatorII::calculate_ArrayList);
        // test("Regex", BasicCalculatorII::calculate_Stack);
        // test("Regex", BasicCalculatorII::calculate_Regex);
    }

    private static void test(String name, Function<String, Integer> solution) {
        System.out.println("== " + name + " ==");
        String[] exps = new String[] {
                "3+2*2",
                "3/2",
                " 3/2 ",
                "3+5/2",
                "3+5 / 2",
                "0",
                "1 + 1",
                " 123 ",
        };
        for (String exp : exps) {
            System.out.println(exp + " = " + solution.apply(exp));
        }
    }

}
