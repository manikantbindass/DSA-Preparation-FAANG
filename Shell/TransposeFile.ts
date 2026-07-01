// ──────────────────────────────────────────────────────────────────────
// LeetCode #194 · Transpose File
// Difficulty : Medium
// Topics     : Shell
// URL        : https://leetcode.com/problems/transpose-file/
// ──────────────────────────────────────────────────────────────────────
// Approach
//   The problem requires transposing a space-separated text file. The
//   accepted solution uses awk to read the file line by line. For each
//   line, it iterates over the fields (columns). On the first line
//   (NR==1), it initializes an array `res` with each field. On subsequent
//   lines, it appends the current field to the corresponding array element
//   separated by a space. After processing all lines, it prints each
//   element of the array on a new line, effectively transposing rows to
//   columns. In TypeScript, we can simulate this by reading the file
//   content, splitting into lines, splitting each line by spaces, and then
//   building the transposed result by iterating over columns and rows.
// 
// Complexity
//   Time  : O(m * n) where m is number of rows and n is number of columns
//   Space : O(m * n) for storing the matrix
// 
// Runtime  : 
// Memory   : 
// 
// Examples
//   Example 1:
//     Input  : a
//     Output : 
// 
// Constraints
// ──────────────────────────────────────────────────────────────────────

import * as fs from 'fs';

function transposeFile(filePath: string): string {
    const content = fs.readFileSync(filePath, 'utf-8');
    const lines = content.trim().split('\n');
    if (lines.length === 0) return '';
    const matrix = lines.map(line => line.split(' '));
    const numRows = matrix.length;
    const numCols = matrix[0].length;
    const result: string[] = [];
    for (let col = 0; col < numCols; col++) {
        const colValues: string[] = [];
        for (let row = 0; row < numRows; row++) {
            colValues.push(matrix[row][col]);
        }
        result.push(colValues.join(' '));
    }
    return result.join('\n');
}

// Example usage:
// const output = transposeFile('file.txt');
// console.log(output);
