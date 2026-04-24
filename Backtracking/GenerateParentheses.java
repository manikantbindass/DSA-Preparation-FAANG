// LeetCode 22 - Generate Parentheses
// Time Complexity: O(4^n / sqrt(n)) | Space Complexity: O(n)
import java.util.ArrayList;
import java.util.List;

public class GenerateParentheses {
    public List<String> generateParenthesis(int n) {
        List<String> answer = new ArrayList<>();
        dfs(n, 0, 0, new StringBuilder(), answer);
        return answer;
    }

    private void dfs(int n, int open, int close, StringBuilder path, List<String> answer) {
        if (open == n && close == n) {
            answer.add(path.toString());
            return;
        }

        if (open < n) {
            path.append('(');
            dfs(n, open + 1, close, path, answer);
            path.deleteCharAt(path.length() - 1);
        }

        if (close < open) {
            path.append(')');
            dfs(n, open, close + 1, path, answer);
            path.deleteCharAt(path.length() - 1);
        }
    }
}
