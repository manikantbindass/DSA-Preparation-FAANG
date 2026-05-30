/*
LeetCode Problem 3161: Block Placement Queries
Problem Number: 3161
Difficulty: Hard
Link: https://leetcode.com/problems/block-placement-queries/

There is an infinite number line. You are given a 2D integer array queries.
The first element of each query is the type:
- Type 1: queries[i] = [1, x]. Place a block at coordinate x.
- Type 2: queries[i] = [2, x, sz]. Check if there is a space of length sz 
  (i.e., a segment of length sz) that does not contain any block and is completely 
  within the range [0, x] (inclusive). Return true if such a space exists, else false.

Example:
Input: queries = [[1,2],[2,3,2],[2,4,2],[1,5],[2,6,2]]
Output: [false,true,true]

Constraints:
- 1 <= queries.length <= 2 * 10^5
- For type 1: 0 <= x <= 10^9
- For type 2: 0 <= x <= 10^9, 1 <= sz <= 10^9

Topics: Array, Binary Search, Ordered Set
Time Complexity: O(n log n) - for operations on sorted structure
Space Complexity: O(n) - for storing positions
*/

package binarysearch

import (
	"sort"
)

func getResults(queries [][]int) []bool {
    result := make([]bool, 0)
    blocks := []int{0} // Starting boundary
    
    for _, q := range queries {
        if q[0] == 1 {
            // Place a block at position x
            x := q[1]
            idx := sort.SearchInts(blocks, x)
            if idx == len(blocks) {
                blocks = append(blocks, x)
            } else {
                blocks = append(blocks[:idx+1], blocks[idx:]...)
                blocks[idx] = x
            }
        } else {
            // Check if a segment of length sz exists within [0, x]
            x := q[1]
            sz := q[2]
            
            maxGap := 0
            for i := 0; i < len(blocks)-1; i++ {
                if blocks[i+1] > x {
                    gap := x - blocks[i]
                    if gap > maxGap {
                        maxGap = gap
                    }
                    break
                } else {
                    gap := blocks[i+1] - blocks[i]
                    if gap > maxGap {
                        maxGap = gap
                    }
                }
            }
            result = append(result, maxGap >= sz)
        }
    }
    return result
}
