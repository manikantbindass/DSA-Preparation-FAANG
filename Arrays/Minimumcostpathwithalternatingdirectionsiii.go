/*
 * LeetCode Problem 0: minimum-cost-path-with-alternating-directions-iii
 * Problem Number: 0
 * Difficulty: Medium
 * Link: https://leetcode.com/problems/minimum-cost-path-with-alternating-directions-iii/
 *
 *
 *
 * Topics: General
 * Time Complexity: See solution
 * Space Complexity: O(1) to O(n)
 * Runtime: 1 ms
 * Memory: 42.8 MB
 */

/*
 * Original Solution (java) — translate to Go:
 *
 * class Solution {
 *     static final int M=1000000007;
 *     public int countValidSequences(int n,int k) {
 *         if (k > n) return 0;
 *         long[] f=new long[n+ 1];
 *         long[] iv= new long[n+1];
 *         f[0] =1;
 *         for (int i= 1;i <= n; i++) f[i]= f[i- 1]* i % M;
 *         iv[n]= pw(f[n], M-2);
 *         for (int i = n;i> 0;i--) iv[i- 1] =iv[i] * i % M;
 *         long all =c(n -1, k-1, f, iv);
 *         long odd= 0;
 *         if (((n -k) & 1)== 0) {
 *             int s= (n - k) / 2;
 *             odd =c(s + k - 1, k - 1, f, iv);
 *         }
 *         long ans=(all - odd) % M;
 *         if (ans < 0) ans+= M;
 *         return (int) ans;
 *     }
 *     long c(int n,int r, long[] f, long[] iv) {
 *         if (r < 0 ||r > n) return 0;
 *         return f[n] * iv[r] % M* iv[n-r] % M;
 *     }
 *     long pw(long a, long b) {
 *         long r = 1;
 *         while (b > 0) {
 *             if ((b & 1) ==1) r=r * a % M;
 *             a=a*a % M;
 *             b>>= 1;
 *         }
 *         return r;
 *     }
 * }
 */

package minimumcostpathwithalternatingdirectionsiii

func solve() interface{} {
	// TODO: Implement Go solution
	return nil
}
