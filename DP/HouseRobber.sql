-- ──────────────────────────────────────────────────────────────────────
-- LeetCode #198 · House Robber
-- Difficulty : Medium
-- Topics     : Array, Dynamic Programming
-- URL        : https://leetcode.com/problems/house-robber/
-- ──────────────────────────────────────────────────────────────────────
-- Approach
--   The problem is solved using dynamic programming. The key insight is
--   that at each house, the robber has two choices: either rob the current
--   house and then skip the next one, or skip the current house and move
--   to the next. This leads to a recurrence relation: dp[i] = max(nums[i]
--   + dp[i+2], dp[i+1]). The solution uses memoization (top-down DP) to
--   avoid redundant calculations. The base case is when i >= n, return 0.
--   The final answer is dp[0].
-- 
-- Complexity
--   Time  : O(n)
--   Space : O(n)
-- 
-- Runtime  : 
-- Memory   : 
-- 
-- Examples
--   Example 1:
--     Input  : nums = [1,2,3,1]
--     Output : 4
--     Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
--   Example 2:
--     Input  : nums = [2,7,9,3,1]
--     Output : 12
--     Explanation: Rob house 1 (money = 2), rob house 3 (money = 9) and rob house 5 (money = 1).
-- 
-- Constraints
--   · 1 <= nums.length <= 100
--   · 0 <= nums[i] <= 400
-- ──────────────────────────────────────────────────────────────────────

-- Since SQL is not typically used for this type of algorithmic problem, we simulate the DP logic using a recursive CTE or iterative approach. Here we use a recursive CTE to compute the maximum amount.
-- Note: This assumes a table 'houses' with columns 'id' (1-indexed) and 'amount'.
WITH RECURSIVE dp AS (
    -- Base case: for the last house, max is its amount
    SELECT id, amount AS max_rob
    FROM houses
    WHERE id = (SELECT MAX(id) FROM houses)
    UNION ALL
    -- Recursive step: for each previous house, compute max of robbing it or skipping
    SELECT h.id, 
           GREATEST(h.amount + COALESCE(n2.max_rob, 0), n1.max_rob) AS max_rob
    FROM houses h
    LEFT JOIN dp n1 ON n1.id = h.id + 1
    LEFT JOIN dp n2 ON n2.id = h.id + 2
    WHERE h.id < (SELECT MAX(id) FROM houses)
)
SELECT max_rob AS result
FROM dp
WHERE id = 1;
