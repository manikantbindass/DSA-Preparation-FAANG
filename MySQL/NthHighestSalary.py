"""
LeetCode Problem 177: Nth Highest Salary
Problem Number: 177
Difficulty: Medium
Link: https://leetcode.com/problems/nth-highest-salary/

Note: This problem is designed for SQL. This Python solution demonstrates
the logic using pandas to find the nth highest salary.

Table: Employee
+-------------+------+
| Column Name | Type |
+-------------+------+
| id          | int  |
| salary      | int  |
+-------------+------+

Write a solution to find the nth highest salary from the Employee table.
If there is no nth highest salary, return None.

Example 1:
Input: 
Employee table:
+----+--------+
| id | salary |
+----+--------+
| 1  | 100    |
| 2  | 200    |
| 3  | 300    |
+----+--------+
n = 2
Output: 200

Example 2:
Input: 
Employee table:
+----+--------+
| id | salary |
+----+--------+
| 1  | 100    |
+----+--------+
n = 2
Output: None

Topics: Database
Time Complexity: O(n log n) - due to sorting
Space Complexity: O(n) - for the result set
"""

import pandas as pd

def get_nth_highest_salary(employee: pd.DataFrame, n: int) -> pd.DataFrame:
    """
    Find the nth highest salary from the Employee table.
    
    Args:
        employee: DataFrame with columns 'id' and 'salary'
        n: The rank of the highest salary to return
        
    Returns:
        DataFrame with column 'getNthHighestSalary(n)' containing the result
    """
    # Get unique salaries sorted in descending order
    unique_salaries = employee['salary'].drop_duplicates().sort_values(ascending=False)
    
    # Check if n is valid (1-indexed)
    if n <= 0 or n > len(unique_salaries):
        result = None
    else:
        result = unique_salaries.iloc[n - 1]
    
    # Return as DataFrame with appropriate column name
    return pd.DataFrame({f'getNthHighestSalary({n})': [result]})


# Example usage
if __name__ == "__main__":
    # Create sample data
    data = {
        'id': [1, 2, 3],
        'salary': [100, 200, 300]
    }
    employee_df = pd.DataFrame(data)
    
    print("Original DataFrame:")
    print(employee_df)
    print("\n" + "="*50 + "\n")
    
    result_df = get_nth_highest_salary(employee_df, 2)
    print("2nd Highest Salary:")
    print(result_df)
    # Output: getNthHighestSalary(2)
    #         200
