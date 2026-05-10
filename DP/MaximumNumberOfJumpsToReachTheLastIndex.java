// LeetCode 2770 - Maximum Number of Jumps to Reach the Last Index
// Time Complexity: O(n^2) | Space Complexity: O(n)
public class MaximumNumberOfJumpsToReachTheLastIndex {
    private Integer[] memo;
    private int[] nums;
    private int n;
    private int target;

    public int maximumJumps(int[] nums, int target) {
        this.nums = nums;
        this.target = target;
        n = nums.length;
        memo = new Integer[n];

        int answer = dfs(0);
        return answer < 0 ? -1 : answer;
    }

    private int dfs(int index) {
        if (index == n - 1) {
            return 0;
        }

        if (memo[index] != null) {
            return memo[index];
        }

        int answer = -(1 << 30);
        for (int next = index + 1; next < n; next++) {
            if (Math.abs(nums[index] - nums[next]) <= target) {
                answer = Math.max(answer, 1 + dfs(next));
            }
        }

        memo[index] = answer;
        return answer;
    }
}
