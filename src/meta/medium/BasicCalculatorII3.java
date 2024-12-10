package meta.medium;

import java.util.function.Function;

public class BasicCalculatorII3 {
    private static final char END = '\r';

    private static class Offset {
        int index;
    }

    public static int calculate(String s) {
        int result = 0;
        Offset offset = new Offset();
        char op = '+';
        while (offset.index < s.length()) {
            int number = nextNumber(s, offset);
            char nextOp = nextOperator(s, offset);
            if (nextOp == END) {
                result = calculate(result, op, number);
                break;
            }
            if (nextOp == '+' || nextOp == '-') {
                result = calculate(result, op, number);
                op = nextOp;
            } else {
                int temp = number;
                do {
                    number = nextNumber(s, offset);
                    temp = calculate(temp, nextOp, number);
                    nextOp = nextOperator(s, offset);
                } while (nextOp == '*' || nextOp == '/');
                result = calculate(result, op, temp);
                if (nextOp == END)
                    break;
                op = nextOp;
            }
        }
        return result;
    }

    private static int calculate(int left, char op, int right) {
        switch (op) {
            case '+':
                return left + right;
            case '-':
                return left - right;
            case '*':
                return left * right;
            default:
                return left / right;
        }
    }

    private static char nextOperator(String s, Offset offset) {
        while (offset.index < s.length()) {
            char c = s.charAt(offset.index++);
            if (c == '+' || c == '-' || c == '*' || c == '/') {
                return c;
            }
        }
        return END;
    }

    private static int nextNumber(String s, Offset offset) {
        int number = 0;
        while (offset.index < s.length()) {
            char c = s.charAt(offset.index);
            if (Character.isDigit(c)) {
                number = number * 10 + Character.getNumericValue(c);
            } else if (c == '+' || c == '-' || c == '*' || c == '/') {
                break;
            }
            offset.index++;
        }
        return number;
    }

    public static void main(String[] args) {
        test("Regex", BasicCalculatorII3::calculate);
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
