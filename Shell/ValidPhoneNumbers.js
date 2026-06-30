// ──────────────────────────────────────────────────────────────────────
// LeetCode #193 · Valid Phone Numbers
// Difficulty : Easy
// Topics     : Shell
// URL        : https://leetcode.com/problems/valid-phone-numbers/
// ──────────────────────────────────────────────────────────────────────
// Approach
//   The problem requires filtering lines from a file that match either of
//   two phone number formats: (xxx) xxx-xxxx or xxx-xxx-xxxx. The accepted
//   solution uses awk with a regex that matches both patterns. For other
//   languages, we read the file line by line, apply the same regex, and
//   print matching lines. The regex pattern is: ^(\d{3}-|\(\d{3}\)
//   )\d{3}-\d{4}$.
// 
// Complexity
//   Time  : O(n)
//   Space : O(1)
// 
// Runtime  : 72 ms
// Memory   : 3.8 MB
// 
// Examples
//   Example 1:
//     Input  : 0
//     Output : 
// 
// Constraints
// ──────────────────────────────────────────────────────────────────────

const fs = require('fs');
const readline = require('readline');

const rl = readline.createInterface({
    input: fs.createReadStream('file.txt'),
    output: process.stdout,
    terminal: false
});

const regex = /^(\d{3}-|\(\d{3}\) )\d{3}-\d{4}$/;

rl.on('line', (line) => {
    if (regex.test(line)) {
        console.log(line);
    }
});
