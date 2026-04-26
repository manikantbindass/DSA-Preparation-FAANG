// LeetCode 78 - Subsets
// Time Complexity: O(n * 2^n) | Space Complexity: O(n)
import java.util.ArrayList;
import java.util.List;

public class Subsets {
    private final List<List<Integer>> answer = new ArrayList<>();
    private final List<Integer> current = new ArrayList<>();
    private int[] nums;

    public List<List<Integer>> subsets(int[] nums) {
        this.nums = nums;
        dfs(0);
        return answer;
    }

    private void dfs(int index) {
        if (index == nums.length) {
            answer.add(new ArrayList<>(current));
            return;
        }

        dfs(index + 1);

        current.add(nums[index]);
        dfs(index + 1);
        current.remove(current.size() - 1);
    }
}
