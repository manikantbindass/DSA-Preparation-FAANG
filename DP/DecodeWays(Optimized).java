// O(1) space optimized version
class Solution {
    public int numDecodings(String s) {
        int n = s.length();
        int prev2 = 1; // dp[i-2]
        int prev1 = 0; // dp[i-1]
        
        for (int i = 1; i <= n; ++i) {
            int curr = 0;
            if (s.charAt(i - 1) != '0') {
                curr = prev1;
            }
            if (i > 1 && s.charAt(i - 2) != '0') {
                int twoDigits = Integer.parseInt(s.substring(i - 2, i));
                if (twoDigits <= 26) {
                    curr += prev2;
                }
            }
            prev2 = prev1;
            prev1 = curr;
        }
        return prev1;
    }
}
