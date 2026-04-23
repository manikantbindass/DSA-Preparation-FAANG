// LeetCode 2121 - Intervals Between Identical Elements
// Time Complexity: O(n) | Space Complexity: O(n)
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IntervalsBetweenIdenticalElements {
    public long[] getDistances(int[] arr) {
        int n = arr.length;
        long[] ans = new long[n];
        Map<Integer, List<Integer>> groups = new HashMap<>();

        for (int i = 0; i < n; i++) {
            groups.computeIfAbsent(arr[i], key -> new ArrayList<>()).add(i);
        }

        for (List<Integer> indices : groups.values()) {
            int m = indices.size();
            long left = 0;
            long right = -1L * m * indices.get(0);

            for (int index : indices) {
                right += index;
            }

            for (int i = 0; i < m; i++) {
                ans[indices.get(i)] = left + right;

                if (i + 1 < m) {
                    long gap = indices.get(i + 1) - indices.get(i);
                    left += gap * (i + 1L);
                    right -= gap * (m - i - 1L);
                }
            }
        }

        return ans;
    }
}
