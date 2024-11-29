package meta.easy;

/**
 * Given a stream of integers and a window size, calculate the moving average of
 * all integers in the sliding window.
 * 
 * Implement the MovingAverage class:
 * 
 * MovingAverage(int size) Initializes the object with the size of the window
 * size.
 * double next(int val) Returns the moving average of the last size values of
 * the stream.
 * 
 * 
 * Example 1:
 * 
 * Input
 * ["MovingAverage", "next", "next", "next", "next"]
 * [[3], [1], [10], [3], [5]]
 * Output
 * [null, 1.0, 5.5, 4.66667, 6.0]
 * 
 * Explanation
 * MovingAverage movingAverage = new MovingAverage(3);
 * movingAverage.next(1); // return 1.0 = 1 / 1
 * movingAverage.next(10); // return 5.5 = (1 + 10) / 2
 * movingAverage.next(3); // return 4.66667 = (1 + 10 + 3) / 3
 * movingAverage.next(5); // return 6.0 = (10 + 3 + 5) / 3
 * 
 * 
 * Constraints:
 * 
 * 1 <= size <= 1000
 * -105 <= val <= 105
 * At most 104 calls will be made to next.
 */
public class MovingAverageArray {

    private int[] queue;

    private int index;
    private double sum;

    public MovingAverageArray(int size) {
        queue = new int[size];
        index = 0;
        sum = 0;
    }

    public double next(int val) {
        if (index < queue.length) {
            queue[index++] = val;
            sum += val;
            return sum / index;
        } else {
            int exitWin = queue[index % queue.length];
            queue[index % queue.length] = val;
            sum = sum - exitWin + val;
            index++;
            return sum / queue.length;
        }
    }

    public static void main(String[] args) {
        MovingAverageArray ma = new MovingAverageArray(3);
        System.err.println(ma.next(1));
        System.err.println(ma.next(10));
        System.err.println(ma.next(3));
        System.err.println(ma.next(5));
        System.err.println(ma.next(3));
        System.err.println(ma.next(3));
        System.err.println(ma.next(3));
        System.err.println(ma.next(12));
    }

}
