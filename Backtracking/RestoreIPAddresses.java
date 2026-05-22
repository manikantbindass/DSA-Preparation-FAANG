/*
 * LeetCode Problem 93: Restore IP Addresses
 * Problem Number: 93
 * Difficulty: Medium
 * Link: https://leetcode.com/problems/restore-ip-addresses/
 * 
 * A valid IP address consists of exactly four integers separated by single dots.
 * Each integer is between 0 and 255 (inclusive) and cannot have leading zeros.
 * 
 * Given a string s containing only digits, return all possible valid IP addresses
 * that can be formed by inserting dots into s. You may return them in any order.
 * 
 * Example 1:
 * Input: s = "25525511135"
 * Output: ["255.255.11.135","255.255.111.35"]
 * 
 * Example 2:
 * Input: s = "0000"
 * Output: ["0.0.0.0"]
 * 
 * Example 3:
 * Input: s = "101023"
 * Output: ["1.0.10.23","1.0.102.3","10.1.0.23","10.10.2.3","101.0.2.3"]
 * 
 * Constraints:
 * - 1 <= s.length <= 20
 * - s consists of digits only.
 * 
 * Topics: String, Backtracking
 * Time Complexity: O(3^4) - constant (at most 3 choices per segment, 4 segments)
 * Space Complexity: O(1) - excluding output space
 */

import java.util.ArrayList;
import java.util.List;

class Solution {
    private int n;
    private String s;
    private List<String> ans = new ArrayList<>();
    private List<String> segments = new ArrayList<>();
    
    public List<String> restoreIpAddresses(String s) {
        n = s.length();
        this.s = s;
        dfs(0);
        return ans;
    }
    
    private void dfs(int start) {
        // If we have 4 segments and used all characters, it's a valid IP
        if (segments.size() == 4 && start == n) {
            ans.add(String.join(".", segments));
            return;
        }
        
        // If we have 4 segments but still have characters left, or vice versa
        if (segments.size() >= 4 || start >= n) {
            return;
        }
        
        int num = 0;
        // Try segment lengths of 1, 2, or 3 digits
        for (int end = start; end < Math.min(start + 3, n); ++end) {
            num = num * 10 + (s.charAt(end) - '0');
            
            // Check for invalid number (>255) or leading zero
            if (num > 255 || (s.charAt(start) == '0' && start != end)) {
                break;
            }
            
            segments.add(s.substring(start, end + 1));
            dfs(end + 1);
            segments.remove(segments.size() - 1);
        }
    }
}
