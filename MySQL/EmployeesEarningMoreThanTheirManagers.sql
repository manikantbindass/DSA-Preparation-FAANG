-- ──────────────────────────────────────────────────────────────────────
-- LeetCode #181 · Employees Earning More Than Their Managers
-- Difficulty : Easy
-- Topics     : Database
-- URL        : https://leetcode.com/problems/employees-earning-more-than-their-managers/
-- ──────────────────────────────────────────────────────────────────────
-- Approach
--   The problem requires finding employees who earn more than their direct
--   managers. The SQL solution uses a self-join on the Employee table:
--   join each employee (e1) with their manager (e2) on e1.managerId =
--   e2.id, then filter where e1.salary > e2.salary. The pandas solution
--   performs an equivalent merge of the DataFrame with itself on managerId
--   and id, then filters rows where salary_x > salary_y, and selects the
--   employee name column.
-- 
-- Complexity
--   Time  : O(n^2) in worst case for self-join (SQL) or merge (pandas), but typically O(n log n) with indexing
--   Space : O(n) for the result set
-- 
-- Runtime  : 399
-- Memory   : 0
-- 
-- Examples
-- 
-- Constraints
-- ──────────────────────────────────────────────────────────────────────

SELECT e1.name AS Employee
FROM Employee e1
JOIN Employee e2 ON e1.managerId = e2.id
WHERE e1.salary > e2.salary;
