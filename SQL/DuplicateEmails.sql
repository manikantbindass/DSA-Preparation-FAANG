-- ──────────────────────────────────────────────────────────────────────
-- LeetCode #182 · Duplicate Emails
-- Difficulty : Easy
-- Topics     : Database
-- URL        : https://leetcode.com/problems/duplicate-emails/
-- ──────────────────────────────────────────────────────────────────────
-- Approach
--   The problem is to find duplicate emails in the Person table. The
--   accepted SQL solution uses a self-join on the Person table, matching
--   rows where the email is the same but the id is different, then
--   selecting distinct emails. This works because each row has a unique
--   id, so joining on id != id ensures we find pairs of different rows
--   with the same email. The DISTINCT ensures each duplicate email appears
--   only once. For pandas, we can group by 'email' and count occurrences,
--   then filter for counts greater than 1, and select the email column.
-- 
-- Complexity
--   Time  : O(n^2) for the self-join in SQL (worst-case), O(n) for pandas groupby
--   Space : O(n) for storing intermediate results
-- 
-- Runtime  : 400 ms
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
FROM Person p1
JOIN Person p2 ON p1.email = p2.email AND p1.id != p2.id;
