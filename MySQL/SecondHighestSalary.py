"""
LeetCode Problem 176: Second Highest Salary
Problem Number: 176
Difficulty: Medium
Link: https://leetcode.com/problems/second-highest-salary/

Note: This problem is designed for SQL. This Python solution demonstrates
the logic using a list of dictionaries to represent the database table.

Table: Employee
+-------------+------+
| Column Name | Type |
+-------------+------+
| id          | int  |
| salary      | int  |
+-------------+------+

Write a solution to find the second highest salary from the Employee table.
If there is no second highest salary, return None.

Example 1:
Input: Employee table:
+----+--------+
| id | salary |
+----+--------+
| 1  | 100    |
| 2  | 200    |
| 3  | 300    |
+----+--------+
Output: 200

Example 2:
Input: Employee table:
+----+--------+
| id | salary |
+----+--------+
| 1  | 100    |
+----+--------+
Output: None

Constraints:
- 1 <= number of employees <= 10^5
- -10^9 <= salary <= 10^9

Topics: Database (Python simulation)
Time Complexity: O(n log n) - sorting the salaries
Space Complexity: O(n) - for the list of salaries
"""

from typing import List, Optional

class Solution:
    def secondHighestSalary(self, employees: List[dict]) -> Optional[int]:
        """
        Find the second highest salary from the employee list.
        
        Args:
            employees: List of dictionaries with keys 'id' and 'salary'
            
        Returns:
            The second highest salary, or None if it doesn't exist
        """
        # Extract all salaries
        salaries = []
        for emp in employees:
            salaries.append(emp['salary'])
        
        # Remove duplicates and sort in descending order
        unique_salaries = sorted(set(salaries), reverse=True)
        
        # Return the second highest if it exists
        if len(unique_salaries) >= 2:
            return unique_salaries[1]
        return None


# Alternative one-liner using list comprehension
class SolutionAlternative:
    def secondHighestSalary(self, employees: List[dict]) -> Optional[int]:
        unique_salaries = sorted({emp['salary'] for emp in employees}, reverse=True)
        return unique_salaries[1] if len(unique_salaries) >= 2 else None


# Example usage
if __name__ == "__main__":
    # Example 1
    employees1 = [
        {"id": 1, "salary": 100},
        {"id": 2, "salary": 200},
        {"id": 3, "salary": 300}
    ]
    
    sol = Solution()
    result1 = sol.secondHighestSalary(employees1)
    print(f"Second highest salary: {result1}")  # Output: 200
    
    # Example 2
    employees2 = [
        {"id": 1, "salary": 100}
    ]
    result2 = sol.secondHighestSalary(employees2)
    print(f"Second highest salary: {result2}")  # Output: None
