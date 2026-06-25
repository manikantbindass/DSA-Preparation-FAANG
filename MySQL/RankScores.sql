/*
 * LeetCode Problem 178: Rank Scores
 * Problem Number: 178
 * Difficulty: Medium
 * Link: https://leetcode.com/problems/rank-scores/
 * 
 * Table: Scores
 * +-------------+---------+
 * | Column Name | Type    |
 * +-------------+---------+
 * | id          | int     |
 * | score       | decimal |
 * +-------------+---------+
 * 
 * Write an SQL query to rank the scores. The ranking should be calculated according to the following rules:
 * - The scores should be ranked from the highest to the lowest.
 * - If there is a tie between two scores, both should have the same ranking.
 * - After a tie, the next ranking number should be the next consecutive integer value.
 * 
 * Example:
 * Input: 
 * Scores table:
 * +----+-------+
 * | id | score |
 * +----+-------+
 * | 1  | 3.50  |
 * | 2  | 3.65  |
 * | 3  | 4.00  |
 * | 4  | 3.85  |
 * | 5  | 4.00  |
 * | 6  | 3.65  |
 * +----+-------+
 * Output: 
 * +-------+------+
 * | score | rank |
 * +-------+------+
 * | 4.00  | 1    |
 * | 4.00  | 1    |
 * | 3.85  | 2    |
 * | 3.65  | 3    |
 * | 3.65  | 3    |
 * | 3.50  | 4    |
 * +-------+------+
 * 
 * Topics: Database
 * Time Complexity: O(n log n) - due to sorting
 * Space Complexity: O(n) - for the result set
 */

SELECT
    score,
    DENSE_RANK() OVER (ORDER BY score DESC) AS 'rank'
FROM Scores;
