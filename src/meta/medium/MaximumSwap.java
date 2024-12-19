package meta.medium;

/**
 * 670. Maximum Swap
 */
public class MaximumSwap {

    public static int maximumSwap(int num) {
        char[] numArr = Integer.toString(num).toCharArray();
        int n = numArr.length;
        int[] maxRightIndex = new int[n];

        maxRightIndex[n - 1] = n - 1;
        for (int i = n - 2; i >= 0; --i) {
            maxRightIndex[i] = (numArr[i] > numArr[maxRightIndex[i + 1]])
                    ? i
                    : maxRightIndex[i + 1];
        }

        for (int i = 0; i < n; ++i) {
            if (numArr[i] < numArr[maxRightIndex[i]]) {
                char temp = numArr[i];
                numArr[i] = numArr[maxRightIndex[i]];
                numArr[maxRightIndex[i]] = temp;
                return Integer.parseInt(new String(numArr));
            }
        }

        return num;
    }

    public static void main(String[] args) {
        int[] test = new int[] { 2736, 9973, 1234, 91234, 8123490, 1993 };
        for (int i : test) {
            System.out.println(i + ": " + maximumSwap(i));
        }
    }

}
