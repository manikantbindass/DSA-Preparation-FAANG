# ──────────────────────────────────────────────────────────────────────
# LeetCode #185 · Department Top Three Salaries
# Difficulty : Hard
# Topics     : Database
# URL        : https://leetcode.com/problems/department-top-three-salaries/
# ──────────────────────────────────────────────────────────────────────
# Approach
#   The problem requires finding employees who earn the top three unique
#   salaries in each department. The accepted SQL solution uses a
#   correlated subquery to count distinct salaries greater than the
#   current employee's salary within the same department. If that count is
#   less than 3, the employee is among the top three earners. The solution
#   joins Employee and Department tables to get department names. For
#   pandas, we can achieve the same by grouping by department, ranking
#   salaries with a dense rank method (handling ties), and filtering ranks
#   <= 3.
# 
# Complexity
#   Time  : O(n log n) due to sorting for ranking in pandas; SQL may vary by DBMS but typically O(n log n) as well
#   Space : O(n) for storing intermediate results
# 
# Runtime  : 1330
# Memory   : 0
# 
# Examples
# 
# Constraints
# ──────────────────────────────────────────────────────────────────────

import pandas as pd

def department_top_three_salaries(employee: pd.DataFrame, department: pd.DataFrame) -> pd.DataFrame:
    # Merge employee with department
    merged = employee.merge(department, left_on='DepartmentId', right_on='Id', suffixes=('_emp', '_dep'))
    # Rename columns for clarity
    merged = merged.rename(columns={'Name_emp': 'Employee', 'Name_dep': 'Department', 'Salary': 'Salary'})
    # Rank salaries within each department using dense rank (handles ties)
    merged['rank'] = merged.groupby('Department')['Salary'].rank(method='dense', ascending=False)
    # Filter top 3 ranks
    result = merged[merged['rank'] <= 3][['Department', 'Employee', 'Salary']]
    # Sort by Department and Salary descending
    result = result.sort_values(['Department', 'Salary'], ascending=[True, False])
    return result
