// ──────────────────────────────────────────────────────────────────────
// LeetCode #192 · Word Frequency
// Difficulty : Medium
// Topics     : Shell
// URL        : https://leetcode.com/problems/word-frequency/
// ──────────────────────────────────────────────────────────────────────
// Approach
//   The approach reads the file, splits it into words (by whitespace),
//   counts occurrences using a hash map, then sorts by frequency
//   descending and prints the results. In Java, we use BufferedReader to
//   read lines, split on whitespace, and a HashMap for counting. In
//   Python, we use collections.Counter and split. In Go, we use
//   bufio.Scanner with default split (words) and a map for counting, then
//   sort a slice of key-value pairs.
// 
// Complexity
//   Time  : O(n log n) due to sorting, where n is the number of unique words
//   Space : O(n) for storing word counts
// 
// Runtime  : 76 ms
// Memory   : 3.8 MB
// 
// Examples
//   Example 1:
//     Input  : a
//     Output : 
// 
// Constraints
// ──────────────────────────────────────────────────────────────────────

package main

import (
	"bufio"
	"fmt"
	"os"
	"sort"
)

func main() {
	file, err := os.Open("words.txt")
	if err != nil {
		fmt.Fprintf(os.Stderr, "error opening file: %v\n", err)
		os.Exit(1)
	}
	defer file.Close()

	freq := make(map[string]int)
	scanner := bufio.NewScanner(file)
	scanner.Split(bufio.ScanWords)
	for scanner.Scan() {
		word := scanner.Text()
		freq[word]++
	}
	if err := scanner.Err(); err != nil {
		fmt.Fprintf(os.Stderr, "error reading file: %v\n", err)
		os.Exit(1)
	}

	type kv struct {
		Key   string
		Value int
	}
	var sorted []kv
	for k, v := range freq {
		sorted = append(sorted, kv{k, v})
	}
	sort.Slice(sorted, func(i, j int) bool {
		return sorted[i].Value > sorted[j].Value
	})

	for _, item := range sorted {
		fmt.Printf("%s %d\n", item.Key, item.Value)
	}
}
