// LeetCode 58 - Length of Last Word
// Time Complexity: O(n) | Space Complexity: O(1)
public class LengthOfLastWord {
    public int lengthOfLastWord(String s) {
        int end = s.length() - 1;

        while (end >= 0 && s.charAt(end) == ' ') {
            end--;
        }

        int start = end;
        while (start >= 0 && s.charAt(start) != ' ') {
            start--;
        }

        return end - start;
    }
}
