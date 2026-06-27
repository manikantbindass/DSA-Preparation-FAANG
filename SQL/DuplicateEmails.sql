-- ──────────────────────────────────────────────────────────────────────
-- LeetCode #182 · Duplicate Emails
-- Difficulty : Easy
-- Topics     : Database
-- URL        : https://leetcode.com/problems/duplicate-emails/
-- ──────────────────────────────────────────────────────────────────────
-- Approach
--   The problem is to find duplicate emails in the Person table. The
--   accepted SQL solution uses a self-join on the Person table where the
--   email matches but the id differs, then selects distinct emails. For
--   pandas, we can group by email and count occurrences, then filter for
--   counts greater than 1. Both approaches are straightforward and
--   efficient.
-- 
-- Complexity
--   Time  : O(n^2) for self-join SQL, O(n) for pandas groupby
--   Space : O(n) for both
-- 
-- Runtime  : 
-- Memory   : 
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
