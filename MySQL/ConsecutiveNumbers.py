"""
LeetCode Problem 180: Consecutive Numbers
Problem Number: 180
Difficulty: Medium
Link: https://leetcode.com/problems/consecutive-numbers/

This is a Python solution that demonstrates the logic using pandas
to find numbers that appear at least three times consecutively.

Example:
Input: logs = [(1, 1), (2, 1), (3, 1), (4, 2), (5, 1), (6, 2), (7, 2)]
Output: [1]
"""

import pandas as pd
from typing import List

def consecutive_numbers(logs: List[tuple]) -> List[int]:
    """
    Find all numbers that appear at least three times consecutively.
    
    Args:
        logs: List of tuples (id, num) representing the Logs table
        
    Returns:
        List of numbers that appear consecutively at least three times
    """
    if not logs:
        return []
    
    df = pd.DataFrame(logs, columns=['id', 'num'])
    df = df.sort_values('id')
    
    # Check consecutive equality
    same_as_prev = df['num'] == df['num'].shift(1)
    same_as_next = df['num'] == df['num'].shift(-1)
    
    # A number appears in a consecutive triple if it's equal to both neighbors
    result = df[same_as_prev & same_as_next]['num'].unique()
    return result.tolist()


# Example usage
if __name__ == "__main__":
    logs = [(1, 1), (2, 1), (3, 1), (4, 2), (5, 1), (6, 2), (7, 2)]
    print(consecutive_numbers(logs))  # Output: [1]
