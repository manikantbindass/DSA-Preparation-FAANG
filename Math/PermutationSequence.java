// LeetCode 60 - Permutation Sequence
// Time Complexity: O(n^2) | Space Complexity: O(n)
public class PermutationSequence {
    public String getPermutation(int n, int k) {
        StringBuilder answer = new StringBuilder();
        boolean[] visited = new boolean[n + 1];

        for (int index = 0; index < n; index++) {
            int factorial = 1;
            for (int value = 1; value < n - index; value++) {
                factorial *= value;
            }

            for (int value = 1; value <= n; value++) {
                if (!visited[value]) {
                    if (k > factorial) {
                        k -= factorial;
                    } else {
                        answer.append(value);
                        visited[value] = true;
                        break;
                    }
                }
            }
        }

        return answer.toString();
    }
}
