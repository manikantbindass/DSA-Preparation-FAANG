/*
 * LeetCode Problem 3804: Maximize Active Section with Trade II
 * Problem Number: 3804
 * Difficulty: Hard
 * Link: https://leetcode.com/problems/maximize-active-section-with-trade-ii/
 *
 * You are given a binary string s of length n, where:
 *
 * 	'1' represents an active section.
 * 	'0' represents an inactive section.
 *
 * You can perform at most one trade to maximize the number of active sections in
 * s. In a trade, you:
 *
 * 	Convert a contiguous block of '1's that is surrounded by '0's to all '0's.
 * 	Afterward, convert a contiguous block of '0's that is surrounded by '1's to all
 * '1's.
 *
 * Additionally, you are given a 2D array queries, where queries[i] = [li, ri]
 * represents a substring s[li...ri].
 *
 * For each query, determine the maximum possible number of active sections in s
 * after making the optimal trade on the substring s[li...ri].
 *
 * Return an array answer, where answer[i] is the result for queries[i].
 *
 * Note
 *
 * 	For each query, treat s[li...ri] as if it is augmented with a '1' at both ends,
 * forming t = '1' + s[li...ri] + '1'. The augmented '1's do not contribute to the
 * final count.
 * 	The queries are independent of each other.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "01", queries = [[0,1]]
 *
 * Output: [1]
 *
 * Explanation:
 *
 * Because there is no block of '1's surrounded by '0's, no valid trade is
 * possible. The maximum number of active sections is 1.
 *
 * Example 2:
 *
 * Input: s = "0100", queries = [[0,3],[0,2],[1,3],[2,3]]
 *
 * Output: [4,3,1,1]
 *
 * Explanation:
 *
 *
 * 	Query [0, 3] &rarr; Substring "0100" &rarr; Augmented to "101001"
 *
 * 	Choose "0100", convert "0100" &rarr; "0000" &rarr; "1111".
 *
 * 	The final string without augmentation is "1111". The maximum number of active
 * sections is 4.
 *
 *
 *
 * 	Query [0, 2] &rarr; Substring "010" &rarr; Augmented to "10101"
 *
 * 	Choose "010", convert "010" &rarr; "000" &rarr; "111".
 *
 * 	The final string without augmentation is "1110". The maximum number of active
 * sections is 3.
 *
 *
 *
 * 	Query [1, 3] &rarr; Substring "100" &rarr; Augmented to "11001"
 *
 * 	Because there is no block of '1's surrounded by '0's, no valid trade is
 * possible. The maximum number of active sections is 1.
 *
 *
 *
 * 	Query [2, 3] &rarr; Substring "00" &rarr; Augmented to "1001"
 *
 * 	Because there is no block of '1's surrounded by '0's, no valid trade is
 * possible. The maximum number of active sections is 1.
 *
 *
 *
 * Example 3:
 *
 * Input: s = "1000100", queries = [[1,5],[0,6],[0,4]]
 *
 * Output: [6,7,2]
 *
 * Explanation:
 *
 *
 * 	Query [1, 5] &rarr; Substring "00010" &rarr; Augmented to "1000101"
 * 	Choose "00010", convert "00010" &rarr; "00000" &rarr; "11111".
 *
 * 	The final string without augmentation is "1111110". The maximum number of
 * active sections is 6.
 *
 *
 *
 * 	Query [0, 6] &rarr; Substring "1000100" &rarr; Augmented to "110001001"
 * 	Choose "000100", convert "000100" &rarr; "000000" &rarr; "111111".
 *
 * 	The final string without augmentation is "1111111". The maximum number of
 * active sections is 7.
 *
 *
 *
 * 	Query [0, 4] &rarr; Substring "10001" &rarr; Augmented to "1100011"
 * 	Because there is no block of '1's surrounded by '0's, no valid trade is
 * possible. The maximum number of active sections is 2.
 *
 *
 *
 * Example 4:
 *
 * Input: s = "01010", queries = [[0,3],[1,4],[1,3]]
 *
 * Output: [4,4,2]
 *
 * Explanation:
 *
 *
 * 	Query [0, 3] &rarr; Substring "0101" &rarr; Augmented to "101011"
 *
 * 	Choose "010", convert "010" &rarr; "000" &rarr; "111".
 *
 * 	The final string without augmentation is "11110". The maximum number of active
 * sections is 4.
 *
 *
 *
 * 	Query [1, 4] &rarr; Substring "1010" &rarr; Augmented to "110101"
 *
 * 	Choose "010", convert "010" &rarr; "000" &rarr; "111".
 *
 * 	The final string without augmentation is "01111". The maximum number of active
 * sections is 4.
 *
 *
 *
 * 	Query [1, 3] &rarr; Substring "101" &rarr; Augmented to "11011"
 *
 * 	Because there is no block of '1's surrounded by '0's, no valid trade is
 * possible. The maximum number of active sections is 2.
 *
 *
 *
 *
 *
 * Constraints:
 *
 * 	1 <= n == s.length <= 105
 * 	1 <= queries.length <= 105
 * 	s[i] is either '0' or '1'.
 * 	queries[i] = [li, ri]
 * 	0 <= li <= ri < n
 *
 * Example 1:
 * Input: s = "01", queries = [[0,1]]
 * Output: [1]
 *
 * Example 2:
 * Input: s = "0100", queries = [[0,3],[0,2],[1,3],[2,3]]
 * Output: [4,3,1,1]
 *
 * Example 3:
 * Input: s = "1000100", queries = [[1,5],[0,6],[0,4]]
 * Output: [6,7,2]
 *
 * Example 4:
 * Input: s = "01010", queries = [[0,3],[1,4],[1,3]]
 * Output: [4,4,2]
 *
 * Constraints:
 * - 1 <= n == s.length <= 105
 * - 1 <= queries.length <= 105
 * - s[i] is either '0' or '1'.
 * - queries[i] = [li, ri]
 * - 0 <= li <= ri < n
 *
 * Topics: Array, String, Binary Search, Segment Tree
 * Time Complexity: O(log n)
 * Space Complexity: O(1) to O(n)
 */

class Solution {
    private int[] logTbl;
    private int[][] sp;
    public List<Integer> maxActiveSectionsAfterTrade(String s, int[][] queries) {
        int n = s.length();
        int totalOnes =0;
        List<int[]> tmp= new ArrayList<>();
        int i = 0;
        while (i < n) {
            if (s.charAt(i)== '1') {
                int j= i;
                while (j < n && s.charAt(j)=='1') j++;
                tmp.add(new int[]{i,j-1});
                totalOnes+= j - i;
                i= j;
            } else i++;
        }
        int m = tmp.size();
        int[] bs = new int[m], be = new int[m], lb =new int[m],rb =new int[m], gain= new int[m];
        for (int k =0; k < m; k++) { bs[k] = tmp.get(k)[0]; be[k] =tmp.get(k)[1]; }
        for (int k =0; k < m; k++) {
            lb[k]= (k == 0) ? 0 : be[k - 1]+1;
            rb[k] =(k == m - 1) ? n - 1 : bs[k +1]- 1;
            gain[k] =(bs[k]- lb[k])+(rb[k]- be[k]);
        }
        buildSparse(gain);
        int q = queries.length;
        List<Integer> res= new ArrayList<>(q);
        for (int[] query : queries) {
            int l = query[0], r= query[1];
            int a = upperBound(bs, l);
            int b = lowerBound(be, r) - 1;
            int best = 0;
            if (a <= b && a < m && b >= 0) {
                best = Math.max(best, clampGain(a, l, r, lb, rb, bs, be));
                best = Math.max(best, clampGain(b, l, r, lb, rb, bs, be));
                if (b - 1 >= a + 1) best = Math.max(best, rangeMax(a + 1, b - 1));
            }
            res.add(totalOnes + best);
        }
        return res;
    }
    private int clampGain(int idx, int l,int r, int[] lb, int[] rb, int[] bs, int[] be) {
        int left =Math.max(l, lb[idx]);
        int right= Math.min(r, rb[idx]);
        int len = be[idx]-bs[idx] + 1;
        return (right- left + 1) - len;
    }
    private void buildSparse(int[] gain) {
        int m = gain.length;
        logTbl = new int[m + 1];
        for (int k= 2; k <=m; k++) logTbl[k] = logTbl[k / 2] + 1;
        int K = (m == 0) ? 1 : logTbl[m] + 1;
        sp = new int[K][Math.max(m, 1)];
        if (m > 0) sp[0] =gain.clone();
        for (int j= 1;j < K; j++) {
            for (int idx = 0; idx + (1 << j) <= m; idx++) {
                sp[j][idx] = Math.max(sp[j - 1][idx], sp[j - 1][idx + (1 << (j - 1))]);
            }
        }
    }
    private int rangeMax(int l, int r) {
        int j = logTbl[r- l+1];
        return Math.max(sp[j][l], sp[j][r - (1 << j) + 1]);
    }
    private int upperBound(int[] a, int x) {
        int lo =0, hi=a.length;
        while (lo< hi) {
            int mid = (lo + hi) >>> 1;
            if (a[mid] <= x) lo= mid +1; else hi= mid;
        }
        return lo;
    }
    private int lowerBound(int[] a, int x) {
        int lo = 0, hi= a.length;
        while (lo < hi) {
            int mid = (lo + hi) >>> 1;
            if (a[mid] < x) lo= mid +1;
            else hi = mid;
        }
        return lo;
    }
}
