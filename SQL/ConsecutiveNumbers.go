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
//   DISTINCT ensures each number is listed once. For other languages, we
//   simulate this logic: in Java/Python/Go, we read the data, sort by id,
//   then iterate to find sequences of three consecutive identical numbers.
//   For SQL, we use the same window function approach. For pandas, we use
//   shift() to create lag and lead columns and filter.
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

type Log struct {
	Id  int
	Num int
}

func FindConsecutiveNumbers(db *sql.DB) ([]int, error) {
	rows, err := db.Query("SELECT id, num FROM Logs ORDER BY id")
	if err != nil {
		return nil, err
	}
	defer rows.Close()

	var logs []Log
	for rows.Next() {
		var l Log
		if err := rows.Scan(&l.Id, &l.Num); err != nil {
			return nil, err
		}
		logs = append(logs, l)
	}

	seen := make(map[int]bool)
	for i := 0; i < len(logs)-2; i++ {
		if logs[i].Num == logs[i+1].Num && logs[i+1].Num == logs[i+2].Num {
			seen[logs[i].Num] = true
		}
	}

	result := make([]int, 0, len(seen))
	for k := range seen {
		result = append(result, k)
	}
	return result, nil
}

func main() {
	// Example usage
	fmt.Println("Run with database connection")
}
