// LeetCode 42 - Trapping Rain Water
// Time Complexity: O(n) | Space Complexity: O(n)
public class TrappingRainWater {
    public int trap(int[] height) {
        int n = height.length;
        if (n == 0) {
            return 0;
        }

        int[] left = new int[n];
        int[] right = new int[n];
        left[0] = height[0];
        right[n - 1] = height[n - 1];

        for (int i = 1; i < n; i++) {
            left[i] = Math.max(left[i - 1], height[i]);
            right[n - i - 1] = Math.max(right[n - i], height[n - i - 1]);
        }

        int trapped = 0;
        for (int i = 0; i < n; i++) {
            trapped += Math.min(left[i], right[i]) - height[i];
        }

        return trapped;
    }
}
