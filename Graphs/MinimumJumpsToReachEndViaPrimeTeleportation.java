// LeetCode 3629 - Minimum Jumps to Reach End via Prime Teleportation
// Time Complexity: O(n log log M + n * d) | Space Complexity: O(M + n)
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MinimumJumpsToReachEndViaPrimeTeleportation {
    private static final int LIMIT = 1_000_001;
    private static final List<Integer>[] FACTORS = new List[LIMIT];

    static {
        for (int value = 0; value < LIMIT; value++) {
            FACTORS[value] = new ArrayList<>();
        }

        for (int factor = 2; factor < LIMIT; factor++) {
            if (FACTORS[factor].isEmpty()) {
                for (int multiple = factor; multiple < LIMIT; multiple += factor) {
                    FACTORS[multiple].add(factor);
                }
            }
        }
    }

    public int minJumps(int[] nums) {
        int n = nums.length;
        Map<Integer, List<Integer>> groups = new HashMap<>();

        for (int index = 0; index < n; index++) {
            for (int factor : FACTORS[nums[index]]) {
                groups.computeIfAbsent(factor, key -> new ArrayList<>()).add(index);
            }
        }

        boolean[] visited = new boolean[n];
        visited[0] = true;
        ArrayDeque<Integer> queue = new ArrayDeque<>();
        queue.offer(0);
        int jumps = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int step = 0; step < size; step++) {
                int index = queue.poll();
                if (index == n - 1) {
                    return jumps;
                }

                if (index + 1 < n && !visited[index + 1]) {
                    visited[index + 1] = true;
                    queue.offer(index + 1);
                }
                if (index - 1 >= 0 && !visited[index - 1]) {
                    visited[index - 1] = true;
                    queue.offer(index - 1);
                }

                List<Integer> nextIndices = groups.get(nums[index]);
                if (nextIndices == null) {
                    continue;
                }

                for (int nextIndex : nextIndices) {
                    if (!visited[nextIndex]) {
                        visited[nextIndex] = true;
                        queue.offer(nextIndex);
                    }
                }
                nextIndices.clear();
            }
            jumps++;
        }

        return -1;
    }
}
