"""
LeetCode Problem 1757: Recyclable and Low Fat Products
Problem Number: 1757
Difficulty: Easy
Link: https://leetcode.com/problems/recyclable-and-low-fat-products/

Table: Products
+-------------+---------+
| Column Name | Type    |
+-------------+---------+
| product_id  | int     |
| low_fats    | enum    |
| recyclable  | enum    |
+-------------+---------+

low_fats is an ENUM of type ('Y', 'N') where 'Y' means low fat and 'N' means not.
recyclable is an ENUM of type ('Y', 'N') where 'Y' means recyclable and 'N' means not.

Write a solution to find the ids of products that are both low fat and recyclable.
Return the result table in any order.

Example 1:
Input: 
Products table:
+-------------+----------+------------+
| product_id  | low_fats | recyclable |
+-------------+----------+------------+
| 0           | Y        | N          |
| 1           | Y        | Y          |
| 2           | N        | Y          |
| 3           | Y        | Y          |
| 4           | N        | N          |
+-------------+----------+------------+
Output: 
+-------------+
| product_id  |
+-------------+
| 1           |
| 3           |
+-------------+

Topics: Database
Time Complexity: O(n) - full table scan
Space Complexity: O(k) - where k is the number of matching products
"""

import pandas as pd

def find_products(products: pd.DataFrame) -> pd.DataFrame:
    """
    Find product IDs that are both low fat and recyclable.
    
    Args:
        products: DataFrame with columns 'product_id', 'low_fats', 'recyclable'
        
    Returns:
        DataFrame with column 'product_id' containing matching products
    """
    # Filter rows where low_fats is 'Y' AND recyclable is 'Y'
    result = products[(products['low_fats'] == 'Y') & (products['recyclable'] == 'Y')]
    
    # Select only the product_id column
    result = result[['product_id']]
    
    return result


# Alternative one-liner
def find_products_one_liner(products: pd.DataFrame) -> pd.DataFrame:
    return products[(products['low_fats'] == 'Y') & (products['recyclable'] == 'Y')][['product_id']]


# Example usage
if __name__ == "__main__":
    # Create sample data
    data = {
        'product_id': [0, 1, 2, 3, 4],
        'low_fats': ['Y', 'Y', 'N', 'Y', 'N'],
        'recyclable': ['N', 'Y', 'Y', 'Y', 'N']
    }
    products_df = pd.DataFrame(data)
    
    print("Original DataFrame:")
    print(products_df)
    print("\n" + "="*50 + "\n")
    
    result_df = find_products(products_df)
    print("Products that are both low fat and recyclable:")
    print(result_df)




|| OR ||

import pandas as pd


def find_products(products: pd.DataFrame) -> pd.DataFrame:
    rs = products[(products["low_fats"] == "Y") & (products["recyclable"] == "Y")]
    rs = rs[["product_id"]]
    return rs
