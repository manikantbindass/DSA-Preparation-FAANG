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

import java.io.*;
import java.util.regex.*;

public class ValidPhoneNumbers {
    public static void main(String[] args) throws IOException {
        String regex = "^(\\d{3}-|\\(\\d{3}\\) )\\d{3}-\\d{4}$";
        Pattern pattern = Pattern.compile(regex);
        try (BufferedReader br = new BufferedReader(new FileReader("file.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (pattern.matcher(line).matches()) {
                    System.out.println(line);
                }
            }
        }
    }
}
