# ──────────────────────────────────────────────────────────────────────
# LeetCode #183 · Customers Who Never Order
# Difficulty : Easy
# Topics     : Database
# URL        : https://leetcode.com/problems/customers-who-never-order/
# ──────────────────────────────────────────────────────────────────────
# Approach
#   The problem requires finding customers who have never placed any
#   order. This can be solved by performing a LEFT JOIN between the
#   Customers table and the Orders table on the customer ID, then
#   filtering for rows where the Orders table has NULL (meaning no
#   matching order). Alternatively, a subquery with NOT IN or NOT EXISTS
#   can be used. The SQL solution uses LEFT JOIN with a NULL check. The
#   pandas solution uses the merge() function with an outer join and
#   indicator, or equivalently, filtering customers whose IDs are not in
#   the Orders' customerId column.
# 
# Complexity
#   Time  : O(n + m) where n is number of customers and m is number of orders
#   Space : O(n) for the result set
# 
# Runtime  : 
# Memory   : 
# 
# Examples
#   Example 1:
#     Input  : 
#     Output : 
# 
# Constraints
# ──────────────────────────────────────────────────────────────────────

import pandas as pd

def find_customers(customers: pd.DataFrame, orders: pd.DataFrame) -> pd.DataFrame:
    # Filter customers whose id is not in orders' customerId
    result = customers[~customers['id'].isin(orders['customerId'])]
    # Select only the name column and rename to Customers
    return result[['name']].rename(columns={'name': 'Customers'})
