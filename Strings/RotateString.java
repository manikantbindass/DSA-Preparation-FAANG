// LeetCode 796 - Rotate String
// Time Complexity: O(n^2) | Space Complexity: O(n)
public class RotateString {
    public boolean rotateString(String s, String goal) {
        return s.length() == goal.length() && (s + s).contains(goal);
    }
}
