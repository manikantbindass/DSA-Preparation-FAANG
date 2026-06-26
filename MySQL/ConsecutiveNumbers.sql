/*
 * LeetCode Problem 180: Consecutive Numbers
 * Problem Number: 180
 * Difficulty: Medium
 * Link: https://leetcode.com/problems/consecutive-numbers/
 * 
 * Table: Logs
 * +-------------+---------+
 * | Column Name | Type    |
 * +-------------+---------+
 * | id          | int     |
 * | num         | varchar |
 * +-------------+---------+
 * 
 * id is the primary key for this table.
 * id is an auto-increment column starting from 1.
 * 
 * Write a solution to find all numbers that appear at least three times consecutively.
 * Return the result table in any order.
 * 
 * Example 1:
 * Input: 
 * Logs table:
 * +----+-----+
 * | id | num |
 * +----+-----+
 * | 1  | 1   |
 * | 2  | 1   |
 * | 3  | 1   |
 * | 4  | 2   |
 * | 5  | 1   |
 * | 6  | 2   |
 * | 7  | 2   |
 * +----+-----+
 * Output: 
 * +-----------------+
 * | ConsecutiveNums |
 * +-----------------+
 * | 1               |
 * +-----------------+
 * Explanation: 1 is the only number that appears consecutively for at least three times.
 * 
 * Constraints:
 * - 1 <= id <= 10^5
 * - 1 <= num <= 10^9
 * 
 * Topics: Database
 * Time Complexity: O(n) - single pass using window functions
 * Space Complexity: O(n) - for the CTE
 */

-- Solution using LEAD and LAG window functions
WITH ConsecutiveCheck AS (
    SELECT
        id,
        num,
        LAG(num, 1) OVER (ORDER BY id) AS prev_num,
        LEAD(num, 1) OVER (ORDER BY id) AS next_num
    FROM Logs
)
SELECT DISTINCT num AS ConsecutiveNums
FROM ConsecutiveCheck
WHERE num = prev_num AND num = next_num;
