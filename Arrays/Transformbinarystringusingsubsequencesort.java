/*
 * LeetCode Problem 0: transform-binary-string-using-subsequence-sort
 * Problem Number: 0
 * Difficulty: Medium
 * Link: https://leetcode.com/problems/transform-binary-string-using-subsequence-sort/
 *
 *
 *
 * Topics: General
 * Time Complexity: See solution
 * Space Complexity: O(1) to O(n)
 * Runtime: 0 ms
 * Memory: 42.7 MB
 */

class Solution {
    public int minimumGroups(String[] words) {
        Set<String> set= new HashSet<>();
        for (String w : words) {
            int n=w.length();
            char[] e =new char[(n + 1) / 2];
            char[] o= new char[n / 2];
            int ei = 0, oi = 0;
            for (int i= 0; i < n; i++) {
                if ((i & 1)== 0) e[ei++] = w.charAt(i);
                else o[oi++]= w.charAt(i);
            }
            String key= canon(e) + '#'+canon(o);
            set.add(key);
        }
        return set.size();
    }
    private String canon(char[] a) {
        int n= a.length;
        if (n== 0) return "";
        int k=minRot(a);
        StringBuilder sb = new StringBuilder(n);
        for (int i = 0; i < n; i++) sb.append(a[(k + i) % n]);
        return sb.toString();
    }
    private int minRot(char[] s) {
        int n= s.length;
        char[] d = new char[2 * n];
        for (int i = 0; i < n; i++) {
            d[i] =s[i];
            d[i+ n]=s[i];
        }
        int[] f= new int[2 * n];
        Arrays.fill(f, -1);
        int k =0;
        for (int j =1; j < 2 * n; j++) {
            char cj= d[j];
            int i = f[j-k- 1];
            while (i !=-1 && cj != d[k + i + 1]) {
                if (cj < d[k+ i + 1]) k= j - i - 1;
                i= f[i];
            }
            if (cj != d[k+ i + 1]) {
                if (cj < d[k]) k= j;
                f[j -k] = -1;
            } else {
                f[j -k] = i + 1;
            }
        }
        return k;
    }
}
