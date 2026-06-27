# ──────────────────────────────────────────────────────────────────────
# LeetCode #184 · Department Highest Salary
# Difficulty : Medium
# Topics     : Database
# URL        : https://leetcode.com/problems/department-highest-salary/
# ──────────────────────────────────────────────────────────────────────
# Approach
#   The problem requires finding employees who have the highest salary in
#   each department. The approach is to first compute the maximum salary
#   per department using a subquery or window function, then join the
#   Employee and Department tables to get the department name, and filter
#   employees whose salary equals the maximum salary for their department.
#   In SQL, this can be done with a subquery in the WHERE clause or using
#   a window function like RANK() or DENSE_RANK(). In pandas, we can group
#   by department, find the max salary, and merge back to filter. The
#   solution ensures that if multiple employees share the highest salary
#   in a department, all are included.
# 
# Complexity
#   Time  : O(n) where n is number of employees (assuming hash-based grouping)
#   Space : O(n) for storing intermediate results
# 
# Runtime  : 108 ms
# Memory   : 0B
# 
# Examples
#   Example 1:
#     Input  : 
#     Output : 
#     Explanation: Max and Jim both have the highest salary in the IT department and Henry has the highest salary in the Sales department.
# 
# Constraints
# ──────────────────────────────────────────────────────────────────────

import pandas as pd

def department_highest_salary(employee: pd.DataFrame, department: pd.DataFrame) -> pd.DataFrame:
    # Find max salary per department
    max_salary = employee.groupby('departmentId')['salary'].max().reset_index()
    max_salary.columns = ['departmentId', 'max_salary']
    # Merge to get employees with that salary
    result = employee.merge(max_salary, on='departmentId')
    result = result[result['salary'] == result['max_salary']]
    # Merge with department names
    result = result.merge(department, left_on='departmentId', right_on='id')
    # Select required columns
    result = result[['name_y', 'name_x', 'salary']]
    result.columns = ['Department', 'Employee', 'Salary']
    return result
