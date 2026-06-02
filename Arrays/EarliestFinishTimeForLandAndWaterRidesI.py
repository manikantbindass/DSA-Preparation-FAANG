"""
LeetCode Problem 3633: Earliest Finish Time for Land and Water Rides I
Problem Number: 3633
Difficulty: Easy
Link: https://leetcode.com/problems/earliest-finish-time-for-land-and-water-rides-i/

You have two types of rides: land rides and water rides.
You can start with either land rides first or water rides first.
You must finish all rides in the chosen order and cannot mix types.

Given arrays:
- landStartTime[i], landDuration[i] for the i-th land ride
- waterStartTime[j], waterDuration[j] for the j-th water ride

You can start a ride only after its start time. After finishing one ride,
you can start the next ride in the sequence immediately if its start time allows.

Return the earliest time you can finish all rides.

Example 1:
Input: landStartTime = [1,2], landDuration = [1,1], waterStartTime = [3,1], waterDuration = [1,1]
Output: 5

Example 2:
Input: landStartTime = [1,2], landDuration = [1,2], waterStartTime = [1,2], waterDuration = [2,1]
Output: 6

Constraints:
- 1 <= landStartTime.length, waterStartTime.length <= 10^5
- 1 <= landStartTime[i], waterStartTime[i] <= 10^9
- 1 <= landDuration[i], waterDuration[i] <= 10^5

Topics: Array, Greedy
Time Complexity: O(n + m) - where n and m are lengths of the arrays
Space Complexity: O(1) - only using constant extra space
"""

from typing import List

class Solution:
    def earliestFinishTime(self,
                           landStartTime: List[int],
                           landDuration: List[int],
                           waterStartTime: List[int],
                           waterDuration: List[int]) -> int:
        
        def find_earliest_time(first_start: List[int],
                               first_duration: List[int],
                               second_start: List[int],
                               second_duration: List[int]) -> int:
            # Find the earliest finishing time of the first ride sequence
            earliest_first_end = float('inf')
            for i in range(len(first_start)):
                earliest_first_end = min(earliest_first_end,
                                         first_start[i] + first_duration[i])
            
            # For each second ride, calculate finish time and find minimum
            answer = float('inf')
            for i in range(len(second_start)):
                start_second = max(earliest_first_end, second_start[i])
                finish_time = start_second + second_duration[i]
                answer = min(answer, finish_time)
            
            return answer
        
        # Try both orders: land first then water, and water first then land
        land_first = find_earliest_time(landStartTime, landDuration,
                                        waterStartTime, waterDuration)
        water_first = find_earliest_time(waterStartTime, waterDuration,
                                        landStartTime, landDuration)
        
        return min(land_first, water_first)
