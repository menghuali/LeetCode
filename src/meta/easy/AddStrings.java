package meta.easy;

import java.util.function.BiFunction;

public class AddStrings {

    public static String addStrings_1(String num1, String num2) {
        StringBuilder result = new StringBuilder(Math.max(num1.length(), num2.length()) + 1);

        int i = 0, j = 0, promotion = 0;
        for (; i < num1.length() && j < num2.length(); j++, i++) {
            int d1 = Character.getNumericValue(num1.charAt(num1.length() - 1 - i));
            int d2 = Character.getNumericValue(num2.charAt(num2.length() - 1 - j));
            int sum = d1 + d2 + promotion;
            promotion = sum / 10;
            result.append(Character.forDigit(sum % 10, 10));
        }

        if (i < num1.length()) {
            addRemaining_2(result, num1, i, promotion);
        } else if (j < num2.length()) {
            addRemaining_2(result, num2, j, promotion);
        } else if (promotion > 0) {
            result.append(Character.forDigit(promotion % 10, 10));
        }

        return result.reverse().toString();
    }

    private static void addRemaining_2(StringBuilder result, String num, int offset, int promotion) {
        for (; offset < num.length(); offset++) {
            int d = Character.getNumericValue(num.charAt(num.length() - 1 - offset));
            int sum = d + promotion;
            promotion = sum / 10;
            result.append(Character.forDigit(sum % 10, 10));
        }
        if (promotion > 0) {
            result.append(Character.forDigit(promotion % 10, 10));
        }
    }

    public static String addStrings_2(String num1, String num2) {
        char[] result = new char[Math.max(num1.length(), num2.length()) + 1];
        result[0] = '0';

        int i = 0, j = 0, promotion = 0;
        for (; i < num1.length() && j < num2.length(); j++, i++) {
            int d1 = num1.charAt(num1.length() - 1 - i) - '0';
            int d2 = num2.charAt(num2.length() - 1 - j) - '0';
            int sum = d1 + d2 + promotion;
            promotion = sum / 10;
            result[result.length - 1 - i] = (char) ((sum % 10) + '0') ;
        }

        if (i < num1.length()) {
            addRemaining_2(result, num1, i, promotion);
        } else if (j < num2.length()) {
            addRemaining_2(result, num2, j, promotion);
        } else if (promotion > 0) {
            result[0] = '1';
        }

        int adjust = result[0] == '0' ? 1 : 0;
        return String.valueOf(result, adjust, result.length - adjust);
    }

    private static void addRemaining_2(char[] result, String num, int offset, int promotion) {
        for (; offset < num.length(); offset++) {
            int d = num.charAt(num.length() - 1 - offset) - '0';
            int sum = d + promotion;
            promotion = sum / 10;
            result[result.length - 1 - offset] = (char) ((sum % 10) + '0');
        }
        if (promotion > 0) {
            result[0] = '1';
        }
    }

    public static void main(String[] args) {
        test("addStrings_1", AddStrings::addStrings_1);
        test("addStrings_2", AddStrings::addStrings_2);
    }

    private static void test(String name, BiFunction<String, String, String> solution) {
        System.out.println(solution.apply("11", "123"));
        System.out.println(solution.apply("456", "77"));
        System.out.println(solution.apply("0", "0"));
        System.out.println(solution.apply("1", "9"));
    }

}
