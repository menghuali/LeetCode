package meta.medium;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * 636. Exclusive Time of Functions
 */
public class ExclusiveTimeOfFunctions {

    public static int[] exclusiveTime(int n, List<String> logs) {
        int[] exclusiveTimes = new int[n];
        Iterator<String> iterator = logs.iterator();
        Job job = Job.parse(iterator.next());
        while (iterator.hasNext()) {
            Job temp = Job.parse(iterator.next());
            if (temp.status == 's') {
                temp.parent = job;
                job = temp;
            } else {
                exclusiveTimes[job.functionID] += temp.timestamp - job.timestamp + 1 - job.idleTime;
                if (job.parent != null) {
                    job.parent.idleTime += temp.timestamp - job.timestamp + 1;
                    job = job.parent;
                }
            }
        }
        return exclusiveTimes;
    }

    private static class Job {
        int functionID;
        char status;
        int timestamp;
        Job parent;
        int idleTime;

        static Job parse(String str) {
            Job log = new Job();
            int firstColon = str.indexOf(':');
            int secondColon = str.indexOf(':', firstColon + 1);
            log.functionID = Integer.parseInt(str.substring(0, firstColon));
            log.status = str.substring(firstColon + 1, secondColon).charAt(0);
            log.timestamp = Integer.parseInt(str.substring(secondColon + 1));
            return log;
        }
    }

    public static void main(String[] args) {
        Object[][] test = new Object[][] {
                new Object[] {
                        2,
                        new String[] { "0:start:0", "1:start:2", "1:end:5", "0:end:6" } },
                new Object[] {
                        1,
                        new String[] { "0:start:0", "0:start:2", "0:end:5", "0:start:6", "0:end:6", "0:end:7" } },
                new Object[] {
                        2,
                        new String[] { "0:start:0", "0:start:2", "0:end:5", "1:start:6", "1:end:6", "0:end:7" } },
                new Object[] {
                        2,
                        new String[] { "0:start:0", "0:start:2", "0:end:5", "1:start:7", "1:end:7", "0:end:8" } },
                new Object[] { 8,
                        new String[] { "0:start:0", "1:start:5", "2:start:6", "3:start:9", "4:start:11", "5:start:12",
                                "6:start:14", "7:start:15", "1:start:24", "1:end:29", "7:end:34", "6:end:37",
                                "5:end:39", "4:end:40", "3:end:45", "0:start:49", "0:end:54", "5:start:55", "5:end:59",
                                "4:start:63", "4:end:66", "2:start:69", "2:end:70", "2:start:74", "6:start:78",
                                "0:start:79", "0:end:80", "6:end:85", "1:start:89", "1:end:93", "2:end:96", "2:end:100",
                                "1:end:102", "2:start:105", "2:end:109", "0:end:114" } }

        };
        for (int i = 0; i < test.length; i++) {
            int n = (int) test[i][0];
            String[] logs = (String[]) test[i][1];
            System.out.println("Input " + i + ": n=" + n + ", logs=" + Arrays.toString(logs));
            System.out.println("Output: " + Arrays.toString(exclusiveTime(n, Arrays.asList(logs))));
        }
    }

}
