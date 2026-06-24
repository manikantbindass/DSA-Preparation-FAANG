"""
LeetCode Problem 175: Combine Two Tables
Problem Number: 175
Difficulty: Easy
Link: https://leetcode.com/problems/combine-two-tables/

Table: Person
+-------------+---------+
| Column Name | Type    |
+-------------+---------+
| personId    | int     |
| lastName    | varchar |
| firstName   | varchar |
+-------------+---------+

Table: Address
+-------------+---------+
| Column Name | Type    |
+-------------+---------+
| addressId   | int     |
| personId    | int     |
| city        | varchar |
| state       | varchar |
+-------------+---------+

Write a solution to report the first name, last name, city, and state of each person
in the Person table. If the address of a personId is not present in the Address table,
report null instead.

Example 1:
Input: 
Person table:
+----------+----------+-----------+
| personId | lastName | firstName |
+----------+----------+-----------+
| 1        | Wang     | Allen     |
| 2        | Alice    | Bob       |
+----------+----------+-----------+
Address table:
+-----------+----------+--------------+-----------+
| addressId | personId | city         | state     |
+-----------+----------+--------------+-----------+
| 1         | 2        | New York City| New York  |
| 2         | 3        | Boston       | MA        |
+-----------+----------+--------------+-----------+
Output: 
+-----------+----------+--------------+-----------+
| firstName | lastName | city         | state     |
+-----------+----------+--------------+-----------+
| Allen     | Wang     | null         | null      |
| Bob       | Alice    | New York City| New York  |
+-----------+----------+--------------+-----------+

Topics: Database
Time Complexity: O(n) - full table scan
Space Complexity: O(1) - excluding output
"""

import pandas as pd

def combine_two_tables(person: pd.DataFrame, address: pd.DataFrame) -> pd.DataFrame:
    """
    Combine Person and Address tables using LEFT JOIN on personId.
    
    Args:
        person: DataFrame with columns 'personId', 'lastName', 'firstName'
        address: DataFrame with columns 'addressId', 'personId', 'city', 'state'
        
    Returns:
        DataFrame with columns 'firstName', 'lastName', 'city', 'state'
    """
    # Perform LEFT JOIN on personId
    result = person.merge(address, on='personId', how='left')
    
    # Select only the required columns
    result = result[['firstName', 'lastName', 'city', 'state']]
    
    return result

# Alternative one-liner
def combine_two_tables_one_liner(person: pd.DataFrame, address: pd.DataFrame) -> pd.DataFrame:
    return person.merge(address, on='personId', how='left')[['firstName', 'lastName', 'city', 'state']]

# Example usage
if __name__ == "__main__":
    # Create sample data
    person_data = {
        'personId': [1, 2],
        'lastName': ['Wang', 'Alice'],
        'firstName': ['Allen', 'Bob']
    }
    address_data = {
        'addressId': [1, 2],
        'personId': [2, 3],
        'city': ['New York City', 'Boston'],
        'state': ['New York', 'MA']
    }
    
    person_df = pd.DataFrame(person_data)
    address_df = pd.DataFrame(address_data)
    
    print("Person DataFrame:")
    print(person_df)
    print("\nAddress DataFrame:")
    print(address_df)
    print("\n" + "="*50 + "\n")
    
    result_df = combine_two_tables(person_df, address_df)
    print("Combined Result:")
    print(result_df)
