/*
LeetCode Problem 3635: Earliest Finish Time for Land and Water Rides II
Problem Number: 3635
Difficulty: Easy
Link: https://leetcode.com/problems/earliest-finish-time-for-land-and-water-rides-ii/

You have two types of rides: land rides and water rides.
You must take all rides of one type first, then all rides of the other type.
Within each type, you can take rides in any order.

Given arrays:
- landStartTime[i], landDuration[i] for the i-th land ride
- waterStartTime[j], waterDuration[j] for the j-th water ride

You can start a ride only after its start time. After finishing one ride,
you can start the next ride immediately if its start time allows.

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
*/

package arrays

import "math"

func earliestFinishTime(landStartTime []int, landDuration []int,
                        waterStartTime []int, waterDuration []int) int {
    
    calculate := func(firstStart, firstDuration, secondStart, secondDuration []int) int {
        // Find the earliest possible finish time for all first-type rides
        earliestFirstEnd := math.MaxInt32
        for i := 0; i < len(firstStart); i++ {
            end := firstStart[i] + firstDuration[i]
            if end < earliestFirstEnd {
                earliestFirstEnd = end
            }
        }
        
        // For each second-type ride, calculate finish time and find minimum
        minFinish := math.MaxInt32
        for i := 0; i < len(secondStart); i++ {
            startSecond := earliestFirstEnd
            if secondStart[i] > startSecond {
                startSecond = secondStart[i]
            }
            finishTime := startSecond + secondDuration[i]
            if finishTime < minFinish {
                minFinish = finishTime
            }
        }
        
        return minFinish
    }
    
    // Try both orders: land first then water, and water first then land
    landFirst := calculate(landStartTime, landDuration, waterStartTime, waterDuration)
    waterFirst := calculate(waterStartTime, waterDuration, landStartTime, landDuration)
    
    if landFirst < waterFirst {
        return landFirst
    }
    return waterFirst
}
