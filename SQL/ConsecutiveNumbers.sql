-- ──────────────────────────────────────────────────────────────────────
-- LeetCode #180 · Consecutive Numbers
-- Difficulty : Medium
-- Topics     : Database
-- URL        : https://leetcode.com/problems/consecutive-numbers/
-- ──────────────────────────────────────────────────────────────────────
-- Approach
--   The problem requires finding numbers that appear at least three times
--   consecutively in the Logs table. The accepted MySQL solution uses
--   window functions LAG and LEAD to compare each row with its previous
--   and next rows. If both the previous and next numbers equal the current
--   number, then the current number appears consecutively three times. The
--   DISTINCT ensures each number is listed once. For other languages, we
--   simulate this logic: in Java/Python/Go, we read the data, sort by id,
--   then iterate to find sequences of three consecutive identical numbers.
--   For SQL, we use the same window function approach. For pandas, we use
--   shift() to create lag and lead columns and filter.
-- 
-- Complexity
--   Time  : O(n)
--   Space : O(n)
-- 
-- Runtime  : 556
-- Memory   : 0
-- 
-- Examples
-- 
-- Constraints
-- ──────────────────────────────────────────────────────────────────────

WITH T AS (
    SELECT
        *,
        LAG(num) OVER (ORDER BY id) AS prev,
        LEAD(num) OVER (ORDER BY id) AS next
    FROM Logs
)
SELECT DISTINCT num AS ConsecutiveNums
FROM T
WHERE num = prev AND num = next;
