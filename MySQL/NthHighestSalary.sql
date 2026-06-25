/*
 * LeetCode Problem 177: Nth Highest Salary
 * Problem Number: 177
 * Difficulty: Medium
 * Link: https://leetcode.com/problems/nth-high-salary/
 * 
 * Table: Employee
 * +-------------+------+
 * | Column Name | Type |
 * +-------------+------+
 * | id          | int  |
 * | salary      | int  |
 * +-------------+------+
 * 
 * Write an SQL query to report the nth highest salary from the Employee table.
 * If there is no nth highest salary, the query should report null.
 * 
 * Example 1:
 * Input: 
 * Employee table:
 * +----+--------+
 * | id | salary |
 * +----+--------+
 * | 1  | 100    |
 * | 2  | 200    |
 * | 3  | 300    |
 * +----+--------+
 * n = 2
 * Output: 
 * +------------------------+
 * | getNthHighestSalary(2) |
 * +------------------------+
 * | 200                    |
 * +------------------------+
 * 
 * Example 2:
 * Input: 
 * Employee table:
 * +----+--------+
 * | id | salary |
 * +----+--------+
 * | 1  | 100    |
 * +----+--------+
 * n = 2
 * Output: 
 * +------------------------+
 * | getNthHighestSalary(2) |
 * +------------------------+
 * | null                   |
 * +------------------------+
 * 
 * Constraints:
 * - 1 <= n <= 100
 * 
 * Topics: Database
 * Time Complexity: O(n log n) - due to sorting
 * Space Complexity: O(n) - for the result set
 */

CREATE FUNCTION getNthHighestSalary(N INT) RETURNS INT
BEGIN
    SET N = N - 1;
    RETURN (
        SELECT DISTINCT salary
        FROM Employee
        ORDER BY salary DESC
        LIMIT 1 OFFSET N
    );
END
