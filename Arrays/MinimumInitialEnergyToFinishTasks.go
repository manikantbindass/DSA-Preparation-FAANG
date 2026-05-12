/*
LeetCode Problem 1665: Minimum Initial Energy to Finish Tasks
Problem Number: 1665
Difficulty: Hard
Link: https://leetcode.com/problems/minimum-initial-energy-to-finish-tasks/

You are given an array tasks where tasks[i] = [actual_i, minimum_i]:
- actual_i is the actual energy required to complete the i-th task.
- minimum_i is the minimum energy required to start the i-th task.

You start with some initial energy. For each task:
1. You must have at least minimum_i energy to start it.
2. After completing the task, you lose actual_i energy.

Return the minimum initial energy to finish all tasks in any order.

Example 1:
Input: tasks = [[1,2],[2,4],[4,8]]
Output: 8
Explanation:
Start with 8 energy -> task with [4,8] (needs 8) -> remaining 4 
-> task with [2,4] (needs 4) -> remaining 2 
-> task with [1,2] (needs 2) -> remaining 1

Example 2:
Input: tasks = [[1,3],[2,4],[10,11],[10,12],[8,9]]
Output: 32

Constraints:
- 1 <= tasks.length <= 10^5
- 1 <= actual_i <= minimum_i <= 10^4

Topics: Array, Greedy, Sorting
Time Complexity: O(n log n) - due to sorting
Space Complexity: O(log n) - for sorting space
*/

package arrays

import "sort"

func minimumEffort(tasks [][]int) int {
    // Sort tasks by (actual - minimum) in ascending order
    // This ensures tasks that require more "buffer" energy are done later
    sort.Slice(tasks, func(i, j int) bool {
        return (tasks[i][0] - tasks[i][1]) < (tasks[j][0] - tasks[j][1])
    })
    
    ans := 0  // Total initial energy needed
    cur := 0  // Current energy available
    
    for _, task := range tasks {
        actual := task[0]
        minimum := task[1]
        
        // If current energy is less than minimum required, add the difference
        if cur < minimum {
            ans += minimum - cur
            cur = minimum
        }
        // Complete the task: subtract actual energy
        cur -= actual
    }
    
    return ans
}
