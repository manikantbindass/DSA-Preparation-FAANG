// LeetCode 76 - Minimum Window Substring
// Time Complexity: O(s + t) | Space Complexity: O(1)
public class MinimumWindowSubstring {
    public String minWindow(String s, String t) {
        int[] need = new int[128];
        int[] window = new int[128];
        for (char c : t.toCharArray()) {
            need[c]++;
        }

        int bestStart = -1;
        int bestLength = s.length() + 1;
        int matched = 0;

        for (int left = 0, right = 0; right < s.length(); right++) {
            char added = s.charAt(right);
            if (++window[added] <= need[added]) {
                matched++;
            }

            while (matched == t.length()) {
                if (right - left + 1 < bestLength) {
                    bestLength = right - left + 1;
                    bestStart = left;
                }

                char removed = s.charAt(left);
                if (window[removed] <= need[removed]) {
                    matched--;
                }
                window[removed]--;
                left++;
            }
        }

        return bestStart < 0 ? "" : s.substring(bestStart, bestStart + bestLength);
    }
}
