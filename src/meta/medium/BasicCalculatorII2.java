package meta.medium;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class BasicCalculatorII2 {

    public static int calculate_Regex(String s) {
        String[] subExps = s.split("\\+");
        int result = 0;
        for (String expr : subExps) {
            result += subtract(expr);
        }
        return result;
    }

    private static int subtract(String exp) {
        String[] subExp = exp.split("-");
        int result = mutiplyAndDivide(subExp[0]);
        for (int i = 1; i < subExp.length; i++) {
            result -= mutiplyAndDivide(subExp[i]);
        }
        return result;
    }

    private static int mutiplyAndDivide(String exp) {
        List<Integer> numbers = new ArrayList<>();
        List<Character> operators = new ArrayList<>();
        int number = 0;
        for (int i = 0; i < exp.length(); i++) {
            char c = exp.charAt(i);
            if (Character.isDigit(c)) {
                number = number * 10 + Character.getNumericValue(c);
            } else if ( c == '*' || c == '/') {
                operators.add(c);
                numbers.add(number);
                number = 0;
            }
        }
        numbers.add(number);
        int result = numbers.get(0);
        for (int i = 0; i < operators.size(); i++) {
            char c = operators.get(i);
            int right = numbers.get(i + 1);
            result = c == '*' ? result * right : result / right;
        }
        return result;
    }

    public static void main(String[] args) {
        test("Regex", BasicCalculatorII2::calculate_Regex);
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
