-- LeetCode Problem 263: Ugly Number
-- Problem Number: 263
-- Difficulty: Easy
-- Link: https://leetcode.com/problems/ugly-number/
--
-- An ugly number is a positive integer which does not have a prime factor other
-- than 2, 3, and 5.
--
-- Given an integer n, return true if n is an ugly number.
--
--
--
-- Example 1:
--
-- Input: n = 6
-- Output: true
-- Explanation: 6 = 2 &times; 3
--
-- Example 2:
--
-- Input: n = 1
-- Output: true
-- Explanation: 1 has no prime factors.
--
-- Example 3:
--
-- Input: n = 14
-- Output: false
-- Explanation: 14 is not ugly since it includes the prime factor 7.
--
--
--
-- Constraints:
--
-- 	-231 <= n <= 231 - 1
--
-- Example 1:
-- Input: n = 6
-- Output: true
-- Explanation: 6 = 2 &times; 3
--
-- Example 2:
-- Input: n = 1
-- Output: true
-- Explanation: 1 has no prime factors.
--
-- Example 3:
-- Input: n = 14
-- Output: false
-- Explanation: 14 is not ugly since it includes the prime factor 7.
--
-- Constraints:
-- - 231 <= n <= 231 - 1
--
-- Topics: Math
-- Time Complexity: See solution
-- Space Complexity: O(1) to O(n)

# Write your MySQL query statement below
SELECT
    request_at AS Day,
    ROUND(AVG(status != 'completed'), 2) AS 'Cancellation Rate'
FROM
    Trips AS t
    JOIN Users AS u1 ON (t.client_id = u1.users_id AND u1.banned = 'No')
    JOIN Users AS u2 ON (t.driver_id = u2.users_id AND u2.banned = 'No')
WHERE request_at BETWEEN '2013-10-01' AND '2013-10-03'
GROUP BY request_at;
