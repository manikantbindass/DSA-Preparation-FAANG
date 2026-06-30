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

import java.io.*;
import java.util.*;

public class WordFrequency {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("words.txt"));
        Map<String, Integer> freq = new HashMap<>();
        String line;
        while ((line = br.readLine()) != null) {
            String[] words = line.trim().split("\\s+");
            for (String w : words) {
                if (!w.isEmpty()) {
                    freq.put(w, freq.getOrDefault(w, 0) + 1);
                }
            }
        }
        br.close();

        List<Map.Entry<String, Integer>> list = new ArrayList<>(freq.entrySet());
        list.sort((a, b) -> b.getValue().compareTo(a.getValue()));

        for (Map.Entry<String, Integer> e : list) {
            System.out.println(e.getKey() + " " + e.getValue());
        }
    }
}
