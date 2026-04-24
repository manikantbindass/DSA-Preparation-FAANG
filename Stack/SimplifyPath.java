// LeetCode 71 - Simplify Path
// Time Complexity: O(n) | Space Complexity: O(n)
import java.util.ArrayDeque;
import java.util.Deque;

public class SimplifyPath {
    public String simplifyPath(String path) {
        Deque<String> stack = new ArrayDeque<>();

        for (String part : path.split("/")) {
            if (part.isEmpty() || ".".equals(part)) {
                continue;
            }
            if ("..".equals(part)) {
                stack.pollLast();
            } else {
                stack.offerLast(part);
            }
        }

        return "/" + String.join("/", stack);
    }
}
