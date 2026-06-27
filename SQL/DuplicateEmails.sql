-- ──────────────────────────────────────────────────────────────────────
-- LeetCode #182 · Duplicate Emails
-- Difficulty : Easy
-- Topics     : Database
-- URL        : https://leetcode.com/problems/duplicate-emails/
-- ──────────────────────────────────────────────────────────────────────
-- Approach
--   The problem requires finding duplicate emails from the Person table.
--   The SQL solution uses a self-join on the Person table, matching rows
--   where the email is the same but the id is different, then selecting
--   distinct emails to avoid duplicates in the result. The pandas solution
--   groups by email, counts occurrences, and filters for counts greater
--   than 1, then returns the email column.
-- 
-- Complexity
--   Time  : O(n^2) for SQL self-join in worst case, O(n) for pandas groupby
--   Space : O(n) for storing intermediate results
-- 
-- Runtime  : 65 ms
-- Memory   : 0B
-- 
-- Examples
--   Example 1:
--     Input  : 
--     Output : 
--     Explanation: a@b.com is repeated two times.
-- 
-- Constraints
-- ──────────────────────────────────────────────────────────────────────

SELECT DISTINCT p1.email
FROM Person p1, Person p2
WHERE p1.id != p2.id AND p1.email = p2.email;
