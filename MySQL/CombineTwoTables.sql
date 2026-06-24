/*
 * LeetCode Problem 175: Combine Two Tables
 * Problem Number: 175
 * Difficulty: Easy
 * Link: https://leetcode.com/problems/combine-two-tables/
 * 
 * Table: Person
 * +-------------+---------+
 * | Column Name | Type    |
 * +-------------+---------+
 * | personId    | int     |
 * | lastName    | varchar |
 * | firstName   | varchar |
 * +-------------+---------+
 * 
 * Table: Address
 * +-------------+---------+
 * | Column Name | Type    |
 * +-------------+---------+
 * | addressId   | int     |
 * | personId    | int     |
 * | city        | varchar |
 * | state       | varchar |
 * +-------------+---------+
 * 
 * Write an SQL query to report the first name, last name, city, and state of each person 
 * in the Person table. If the address of a personId is not present in the Address table, 
 * report null instead.
 * 
 * Example 1:
 * Input: 
 * Person table:
 * +----------+----------+-----------+
 * | personId | lastName | firstName |
 * +----------+----------+-----------+
 * | 1        | Wang     | Allen     |
 * | 2        | Alice    | Bob       |
 * +----------+----------+-----------+
 * Address table:
 * +-----------+----------+--------------+-----------+
 * | addressId | personId | city         | state     |
 * +-----------+----------+--------------+-----------+
 * | 1         | 2        | New York City| New York  |
 * | 2         | 3        | Boston       | MA        |
 * +-----------+----------+--------------+-----------+
 * Output: 
 * +-----------+----------+--------------+-----------+
 * | firstName | lastName | city         | state     |
 * +-----------+----------+--------------+-----------+
 * | Allen     | Wang     | null         | null      |
 * | Bob       | Alice    | New York City| New York  |
 * +-----------+----------+--------------+-----------+
 * 
 * Constraints:
 * - 1 <= personId <= 1000
 * - 1 <= addressId <= 1000
 * 
 * Topics: Database
 * Time Complexity: O(n) - full table scan
 * Space Complexity: O(1) - excluding output
 */

SELECT firstName, lastName, city, state
FROM Person
LEFT JOIN Address USING (personId);
