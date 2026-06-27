-- ──────────────────────────────────────────────────────────────────────
-- LeetCode #184 · Department Highest Salary
-- Difficulty : Medium
-- Topics     : Database
-- URL        : https://leetcode.com/problems/department-highest-salary/
-- ──────────────────────────────────────────────────────────────────────
-- Approach
--   The problem requires finding employees who have the highest salary in
--   each department. The approach is to first compute the maximum salary
--   per department using a subquery or window function, then join the
--   Employee and Department tables to get the department name, and filter
--   employees whose salary equals the maximum salary for their department.
--   In SQL, this can be done with a subquery in the WHERE clause or using
--   a window function like RANK() or DENSE_RANK(). In pandas, we can group
--   by department, find the max salary, and merge back to filter. The
--   solution ensures that if multiple employees share the highest salary
--   in a department, all are included.
-- 
-- Complexity
--   Time  : O(n) where n is number of employees (assuming hash-based grouping)
--   Space : O(n) for storing intermediate results
-- 
-- Runtime  : 108 ms
-- Memory   : 0B
-- 
-- Examples
--   Example 1:
--     Input  : 
--     Output : 
--     Explanation: Max and Jim both have the highest salary in the IT department and Henry has the highest salary in the Sales department.
-- 
-- Constraints
-- ──────────────────────────────────────────────────────────────────────

SELECT d.name AS Department, e.name AS Employee, e.salary AS Salary
FROM Employee e
JOIN Department d ON e.departmentId = d.id
WHERE (e.departmentId, e.salary) IN (
    SELECT departmentId, MAX(salary)
    FROM Employee
    GROUP BY departmentId
);
