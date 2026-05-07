// LeetCode 3660 - Jump Game IX
// Time Complexity: O(n) | Space Complexity: O(n)
public class JumpGameIX {
    public int[] maxValue(int[] nums) {
        int n = nums.length;
        int[] answer = new int[n];
        int[] prefixMax = new int[n];
        prefixMax[0] = nums[0];

        for (int index = 1; index < n; index++) {
            prefixMax[index] = Math.max(prefixMax[index - 1], nums[index]);
        }

        int suffixMin = Integer.MAX_VALUE;
        for (int index = n - 1; index >= 0; index--) {
            if (prefixMax[index] > suffixMin && index + 1 < n) {
                answer[index] = answer[index + 1];
            } else {
                answer[index] = prefixMax[index];
            }
            suffixMin = Math.min(suffixMin, nums[index]);
        }

        return answer;
    }
}
