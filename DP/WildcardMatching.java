// LeetCode 44 - Wildcard Matching
// Time Complexity: O(m * n) | Space Complexity: O(m * n)
public class WildcardMatching {
    private Boolean[][] memo;
    private char[] text;
    private char[] pattern;
    private int textLength;
    private int patternLength;

    public boolean isMatch(String s, String p) {
        text = s.toCharArray();
        pattern = p.toCharArray();
        textLength = text.length;
        patternLength = pattern.length;
        memo = new Boolean[textLength][patternLength];
        return dfs(0, 0);
    }

    private boolean dfs(int textIndex, int patternIndex) {
        if (textIndex >= textLength) {
            return patternIndex >= patternLength
                    || (pattern[patternIndex] == '*' && dfs(textIndex, patternIndex + 1));
        }

        if (patternIndex >= patternLength) {
            return false;
        }

        if (memo[textIndex][patternIndex] != null) {
            return memo[textIndex][patternIndex];
        }

        if (pattern[patternIndex] == '*') {
            memo[textIndex][patternIndex] =
                    dfs(textIndex + 1, patternIndex)
                            || dfs(textIndex + 1, patternIndex + 1)
                            || dfs(textIndex, patternIndex + 1);
        } else {
            memo[textIndex][patternIndex] =
                    (pattern[patternIndex] == '?' || text[textIndex] == pattern[patternIndex])
                            && dfs(textIndex + 1, patternIndex + 1);
        }

        return memo[textIndex][patternIndex];
    }
}
