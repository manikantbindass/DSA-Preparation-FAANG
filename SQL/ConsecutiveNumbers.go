// ──────────────────────────────────────────────────────────────────────
// LeetCode #180 · Consecutive Numbers
// Difficulty : Medium
// Topics     : Database
// URL        : https://leetcode.com/problems/consecutive-numbers/
// ──────────────────────────────────────────────────────────────────────
// Approach
//   The problem requires finding numbers that appear at least three times
//   consecutively in the Logs table. The accepted MySQL solution uses
//   window functions LAG and LEAD to compare each row with its previous
//   and next rows. If both the previous and next numbers equal the current
//   number, then the current number appears consecutively three times. The
//   DISTINCT ensures each number is listed only once. For other languages,
//   we simulate this logic: in Java/Python/Go, we read the data, sort by
//   id, then iterate checking for three consecutive equal values. For SQL,
//   we use the same window function approach. For pandas, we use shift to
//   create lag and lead columns and filter.
// 
// Complexity
//   Time  : O(n)
//   Space : O(n)
// 
// Runtime  : 556
// Memory   : 0
// 
// Examples
// 
// Constraints
// ──────────────────────────────────────────────────────────────────────

package main

import (
	"database/sql"
	"fmt"
)

func findConsecutiveNumbers(db *sql.DB) ([]int, error) {
	rows, err := db.Query("SELECT id, num FROM Logs ORDER BY id")
	if err != nil {
		return nil, err
	}
	defer rows.Close()

	var ids []int
	var nums []int
	for rows.Next() {
		var id, num int
		if err := rows.Scan(&id, &num); err != nil {
			return nil, err
		}
		ids = append(ids, id)
		nums = append(nums, num)
	}

	resultSet := make(map[int]bool)
	for i := 1; i < len(nums)-1; i++ {
		if nums[i] == nums[i-1] && nums[i] == nums[i+1] {
			resultSet[nums[i]] = true
		}
	}

	var result []int
	for num := range resultSet {
		result = append(result, num)
	}
	return result, nil
}

func main() {
	// Example usage
	fmt.Println("Run with database connection")
}
