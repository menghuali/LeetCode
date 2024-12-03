package meta.medium;

public class Pow {
    public static double myPow_BrutalForce(double x, int n) {
        // if n == 0
        if (n == 0)
            return 1;
        // if x == 0
        if (x == 0)
            if (n < 0)
                return Double.POSITIVE_INFINITY;
            else
                return 0;
        // if x == 1
        if (x == 1)
            return 1;

        int nabs = Math.abs(n);
        // if x == -1
        if (x == -1)
            return nabs % 2 == 0 ? 1 : -1;

        // else
        double result = x;
        while (nabs-- > 1) {
            result *= x;
        }
        return n < 0 ? (1 / result) : result;
    }

    private double binaryExp(double x, long n) {
        // Base case, to stop recursive calls.
        if (n == 0) {
            return 1;
        }

        // Handle case where, n < 0.
        if (n < 0) {
            return 1.0 / binaryExp(x, -1 * n);
        }

        // Perform Binary Exponentiation.
        // If 'n' is odd we perform Binary Exponentiation on 'n - 1' and multiply result
        // with 'x'.
        if (n % 2 == 1) {
            return x * binaryExp(x * x, (n - 1) / 2);
        }
        // Otherwise we calculate result by performing Binary Exponentiation on 'n'.
        else {
            return binaryExp(x * x, n / 2);
        }
    }

    public double myPow(double x, int n) {
        return binaryExp(x, (long) n);
    }

    public static double myPow_solution2(double x, int n) {
        if (n < 0) {
            n = -n;
            x = 1 / x;
        }

        double pow = 1;

        while (n != 0) {
            if ((n & 1) != 0) {
                pow *= x;
            }

            x *= x;
            n >>>= 1;

        }
        return pow;
    }

    public static void main(String[] args) {
        // test(-1, 4);
        // test(-1, 3);
        // test(0, 4);
        // test(0, -4);
        // test(1, 4);
        // test(1, -4);
        // test(2, 10);
        // test(2.1, 3);
        // test(2, -2);
        test(2, -2147483648);
    }

    private static void test(double x, int n) {
        System.out.println(Math.pow(x, n) + ":" + myPow_BrutalForce(x, n));
    }

}
