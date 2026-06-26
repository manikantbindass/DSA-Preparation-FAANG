# ──────────────────────────────────────────────────────────────────────
# LeetCode #180 · Consecutive Numbers
# Difficulty : Medium
# Topics     : Database
# URL        : https://leetcode.com/problems/consecutive-numbers/
# ──────────────────────────────────────────────────────────────────────
# Approach
#   The problem requires finding numbers that appear at least three times
#   consecutively in the Logs table. The accepted MySQL solution uses
#   window functions LAG and LEAD to compare each row with its previous
#   and next rows. If both the previous and next numbers equal the current
#   number, then the current number appears consecutively three times. The
#   DISTINCT ensures each number is listed once. For other languages, we
#   simulate this logic: in Java/Python/Go, we read the data, sort by id,
#   then scan for consecutive equal numbers. For SQL, we use the same
#   window function approach. For pandas, we use shift to create lag and
#   lead columns and filter.
# 
# Complexity
#   Time  : O(n) where n is number of rows in Logs
#   Space : O(1) extra space (excluding output)
# 
# Runtime  : 556
# Memory   : 0
# 
# Examples
# 
# Constraints
# ──────────────────────────────────────────────────────────────────────

import pandas as pd

def consecutive_numbers(logs: pd.DataFrame) -> pd.DataFrame:
    logs = logs.sort_values('id')
    logs['prev'] = logs['num'].shift(1)
    logs['next'] = logs['num'].shift(-1)
    result = logs[(logs['num'] == logs['prev']) & (logs['num'] == logs['next'])]['num'].unique()
    return pd.DataFrame({'ConsecutiveNums': result})
