package meta.medium;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class SimplifyPath {

    public static String simplifyPath_SplitRegex(String path) {
        String[] split = path.split("/+");
        List<String> paths = new ArrayList<>(split.length + 1);
        for (String str : split) {
            if (str.equals(".") || str.isEmpty())
                continue;
            else if (str.equals("..")) {
                if (!paths.isEmpty())
                    paths.remove(paths.size() - 1);
            } else
                paths.add(str);
        }
        StringBuilder builder = new StringBuilder(path.length());
        for (String str : paths) {
            builder.append('/').append(str);
        }
        return builder.length() > 0 ? builder.toString() : "/";
    }

    public static String simplifyPath(String path) {
        // Iterate through every char of of the path, from left to right.
        // Temporarily hold he current path portion in a StringBuilder with the
        // following rules
        // If current char is '/', chech and merge the current portion to stack.
        // If current portion is ., ignore it
        // If current portion is .., popup stack
        // If current portion is empty, ingore.
        // Else add portion to the stack.

        List<String> paths = new ArrayList<>(path.length());
        StringBuilder builder = new StringBuilder(path.length());
        for (int i = 0; i < path.length(); i++) {
            char ch = path.charAt(i);
            if (ch == '/') {
                handlePathPortion(paths, builder);
            } else {
                builder.append(ch);
            }
        }
        handlePathPortion(paths, builder);

        if (paths.size() > 0) {
            StringBuilder pathBuilder = new StringBuilder(path.length());
            for (String str : paths) {
                pathBuilder.append('/').append(str);
            }
            return pathBuilder.toString();
        } else
            return "/";
    }

    private static void handlePathPortion(List<String> paths, StringBuilder builder) {
        String str = builder.toString();
        switch (str) {
            case ".":
            case "":
                break;
            case "..":
                if (!paths.isEmpty())
                    paths.remove(paths.size() - 1);
                break;
            default:
                paths.add(str);
                break;
        }
        builder.delete(0, builder.length());
    }

    public static void main(String[] args) {
        test("SplitRegex", SimplifyPath::simplifyPath);
    }

    private static void test(String name, Function<String, String> solution) {
        System.out.println(String.format("=== %s ===", name));
        String[] testCases = new String[] { "/home/", "/home//foo/", "/home/user/Documents/../Pictures", "/../",
                "/.../a/../b/c/../d/./" };
        for (String testCase : testCases) {
            System.out.println("Input: " + testCase);
            System.out.println("Output: " + solution.apply(testCase));
            System.out.println();
        }
    }

}
