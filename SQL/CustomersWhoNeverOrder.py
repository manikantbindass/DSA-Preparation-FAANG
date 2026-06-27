# ──────────────────────────────────────────────────────────────────────
# LeetCode #183 · Customers Who Never Order
# Difficulty : Easy
# Topics     : Database
# URL        : https://leetcode.com/problems/customers-who-never-order/
# ──────────────────────────────────────────────────────────────────────
# Approach
#   The problem asks for customers who have never placed an order. The SQL
#   solution uses a LEFT JOIN between Customers and Orders on customer ID,
#   then filters for rows where the order ID is NULL, meaning no matching
#   order exists. The pandas solution performs a similar operation: it
#   merges the two DataFrames with a left join on id and customerId, then
#   selects rows where the order id is NaN, and finally extracts the
#   customer names.
# 
# Complexity
#   Time  : O(n + m) where n is number of customers and m is number of orders
#   Space : O(n) for the result
# 
# Runtime  : 126 ms
# Memory   : 0B
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
    merged = customers.merge(orders, left_on='id', right_on='customerId', how='left')
    never_ordered = merged[merged['id_y'].isna()]
    result = never_ordered[['name']].rename(columns={'name': 'Customers'})
    return result
