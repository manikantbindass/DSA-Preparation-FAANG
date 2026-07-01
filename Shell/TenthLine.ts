// ──────────────────────────────────────────────────────────────────────
// LeetCode #195 · Tenth Line
// Difficulty : Easy
// Topics     : Shell
// URL        : https://leetcode.com/problems/tenth-line/
// ──────────────────────────────────────────────────────────────────────
// Approach
//   The problem requires printing the 10th line of a file. In TypeScript,
//   we can read the file using Node.js's fs module, split the content by
//   newline, and output the 10th line if it exists. If the file has fewer
//   than 10 lines, we output nothing (or handle as needed). The solution
//   uses readFileSync for simplicity, but for production, asynchronous
//   reading might be preferred.
// 
// Complexity
//   Time  : O(n) where n is the number of lines in the file
//   Space : O(n) to store the lines array
// 
// Runtime  : 
// Memory   : 
// 
// Examples
//   Example 1:
//     Input  : Line 1\nLine 2\nLine 3\nLine 4\nLine 5\nLine 6\nLine 7\nLine 8\nLine 9\nLine 10
//     Output : 
// 
// Constraints
// ──────────────────────────────────────────────────────────────────────

import * as fs from 'fs';

// Read the file and output the tenth line
const filePath = 'file.txt';
try {
  const data = fs.readFileSync(filePath, 'utf8');
  const lines = data.split('\n');
  // Lines are 0-indexed, so line 10 is index 9
  if (lines.length >= 10) {
    console.log(lines[9]);
  }
  // If less than 10 lines, output nothing (or handle as needed)
} catch (err) {
  console.error('Error reading file:', err);
}
