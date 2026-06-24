/*
 * LeetCode Problem 176: Second Highest Salary
 * Problem Number: 176
 * Difficulty: Medium
 * Link: https://leetcode.com/problems/second-highest-salary/
 * 
 * Table: Employee
 * +-------------+------+
 * | Column Name | Type |
 * +-------------+------+
 * | id          | int  |
 * | salary      | int  |
 * +-------------+------+
 * 
 * Write a solution to find the second highest salary from the Employee table.
 * If there is no second highest salary, return null.
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
 * Output: 
 * +---------------------+
 * | SecondHighestSalary |
 * +---------------------+
 * | 200                 |
 * +---------------------+
 * 
 * Example 2:
 * Input: 
 * Employee table:
 * +----+--------+
 * | id | salary |
 * +----+--------+
 * | 1  | 100    |
 * +----+--------+
 * Output: 
 * +---------------------+
 * | SecondHighestSalary |
 * +---------------------+
 * | null                |
 * +---------------------+
 * 
 * Topics: Database
 * Time Complexity: O(n) - table scan
 * Space Complexity: O(1) - excluding output
 */

-- Solution 1: Using OFFSET with LIMIT
SELECT (
    SELECT DISTINCT salary
    FROM Employee
    ORDER BY salary DESC
    LIMIT 1 OFFSET 1
) AS SecondHighestSalary;

-- Solution 2: Using MAX with subquery (handles null automatically)
SELECT MAX(salary) AS SecondHighestSalary
FROM Employee
WHERE salary < (SELECT MAX(salary) FROM Employee);
