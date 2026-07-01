-- ──────────────────────────────────────────────────────────────────────
-- LeetCode #197 · Rising Temperature
-- Difficulty : Easy
-- Topics     : Database
-- URL        : https://leetcode.com/problems/rising-temperature/
-- ──────────────────────────────────────────────────────────────────────
-- Approach
--   The problem requires finding dates where the temperature is higher
--   than the previous day. The SQL solution uses a self-join on the
--   Weather table, matching each row (w1) with the row from the previous
--   day (w2) using DATEDIFF to ensure exactly one day difference, and
--   filtering where w1.temperature > w2.temperature. The pandas solution
--   sorts the DataFrame by recordDate, then uses shift() to compare each
--   day's temperature with the previous day's, and selects the ids where
--   the condition holds.
-- 
-- Complexity
--   Time  : O(n log n) due to sorting in pandas; SQL may vary but typically O(n log n) for join
--   Space : O(n) for storing intermediate results
-- 
-- Runtime  : 74 ms
-- Memory   : 0B
-- 
-- Examples
--   Example 1:
--     Input  : 
--     Output : 
-- 
-- Constraints
-- ──────────────────────────────────────────────────────────────────────

SELECT w1.id
FROM
    Weather AS w1
    JOIN Weather AS w2
        ON DATEDIFF(w1.recordDate, w2.recordDate) = 1 AND w1.temperature > w2.temperature;
