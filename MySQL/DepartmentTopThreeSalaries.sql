-- ──────────────────────────────────────────────────────────────────────
-- LeetCode #185 · Department Top Three Salaries
-- Difficulty : Hard
-- Topics     : Database
-- URL        : https://leetcode.com/problems/department-top-three-salaries/
-- ──────────────────────────────────────────────────────────────────────
-- Approach
--   The problem requires finding employees who earn the top three unique
--   salaries in each department. The accepted SQL solution uses a
--   correlated subquery to count distinct salaries greater than the
--   current employee's salary within the same department. If that count is
--   less than 3, the employee is among the top three earners. The solution
--   joins Employee and Department tables to get department names. For
--   pandas, we can achieve the same by grouping by department, ranking
--   salaries with a dense rank method (handling ties), and filtering ranks
--   <= 3.
-- 
-- Complexity
--   Time  : O(n log n) due to sorting for ranking in pandas; SQL may vary by DBMS but typically O(n log n) as well
--   Space : O(n) for storing intermediate results
-- 
-- Runtime  : 1330
-- Memory   : 0
-- 
-- Examples
-- 
-- Constraints
-- ──────────────────────────────────────────────────────────────────────

SELECT
    d.Name AS Department,
    e.Name AS Employee,
    e.Salary
FROM Employee e
JOIN Department d ON e.DepartmentId = d.Id
WHERE (
    SELECT COUNT(DISTINCT e2.Salary)
    FROM Employee e2
    WHERE e2.DepartmentId = e.DepartmentId
      AND e2.Salary > e.Salary
) < 3
ORDER BY Department, Salary DESC;
