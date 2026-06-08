/*
LeetCode Problem 134: Gas Station
Problem Number: 134
Difficulty: Medium
Link: https://leetcode.com/problems/gas-station/

There are n gas stations along a circular route, where the amount of gas at station i is gas[i].
You have a car with an unlimited gas tank and it costs cost[i] of gas to travel from station i
to its next station (i+1). You begin the journey with an empty tank at one of the gas stations.

Given two integer arrays gas and cost, return the starting gas station's index if you can travel
around the circuit once in the clockwise direction, otherwise return -1. If there exists a
solution, it is guaranteed to be unique.

Example 1:
Input: gas = [1,2,3,4,5], cost = [3,4,5,1,2]
Output: 3
Explanation: Start at station 3 (index 3) with 4 gas, travel to 4 (5 gas used), get 5 gas,
             travel to 0 (1 gas used), get 1 gas, travel to 1 (2 gas used), get 2 gas,
             travel to 2 (3 gas used), get 3 gas, travel back to 3.

Example 2:
Input: gas = [2,3,4], cost = [3,4,3]
Output: -1

Constraints:
- n == gas.length == cost.length
- 1 <= n <= 10^5
- 0 <= gas[i], cost[i] <= 10^4

Topics: Array, Greedy
Time Complexity: O(n) - single pass through the arrays
Space Complexity: O(1) - only using constant extra space
*/

package arrays

func canCompleteCircuit(gas []int, cost []int) int {
    n := len(gas)
    totalTank := 0
    currentTank := 0
    startStation := 0
    
    for i := 0; i < n; i++ {
        netGain := gas[i] - cost[i]
        totalTank += netGain
        currentTank += netGain
        
        // If current tank is negative, we cannot start from previous start station
        if currentTank < 0 {
            startStation = i + 1
            currentTank = 0
        }
    }
    
    // If total gas is less than total cost, impossible to complete circuit
    if totalTank >= 0 {
        return startStation
    }
    return -1
}
