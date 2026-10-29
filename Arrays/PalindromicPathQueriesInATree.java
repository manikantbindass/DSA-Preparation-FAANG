/*
 * LeetCode Problem 4209: Palindromic Path Queries in a Tree
 * Problem Number: 4209
 * Difficulty: Hard
 * Link: https://leetcode.com/problems/palindromic-path-queries-in-a-tree/
 *
 * You are given an undirected tree with n nodes labeled 0 to n - 1. This is
 * represented by a 2D array edges of length n - 1, where edges[i] = [ui, vi]
 * indicates an undirected edge between nodes ui and vi.
 *
 * You are also given a string s of length n consisting of lowercase English
 * letters, where s[i] represents the character assigned to node i.
 *
 * You are also given a string array queries, where each queries[i] is either:
 *
 * 	"update ui c": Change the character at node ui to c. Formally, update s[ui] =
 * c.
 * 	"query ui vi": Determine whether the string formed by the characters on the
 * unique path from ui to vi (inclusive) can be rearranged into a palindrome.
 *
 * Return a boolean array answer, where answer[j] is true if the jth query of type
 * "query ui vi"​​​​​​​ can be rearranged into a palindrome, and false otherwise.
 *
 *
 *
 * Example 1:
 *
 * Input: n = 3, edges = [[0,1],[1,2]], s = "aac", queries = ["query 0 2","update 1
 * b","query 0 2"]
 *
 * Output: [true,false]
 *
 * Explanation:
 *
 * 	"query 0 2": Path 0 &rarr; 1 &rarr; 2 gives "aac", which can be rearranged to
 * form "aca", a palindrome. Thus, answer[0] = true.
 * 	"update 1 b": Update node 1 to 'b', now s = "abc".
 * 	"query 0 2": Path characters are "abc", which cannot be rearranged to form a
 * palindrome. Thus, answer[1] = false.
 *
 * Thus, answer = [true, false].
 *
 * Example 2:
 *
 * Input: n = 4, edges = [[0,1],[0,2],[0,3]], s = "abca", queries = ["query 1
 * 2","update 0 b","query 2 3","update 3 a","query 1 3"]
 *
 * Output: [false,false,true]
 *
 * Explanation:
 *
 * 	"query 1 2": Path 1 &rarr; 0 &rarr; 2 gives "bac", which cannot be rearranged
 * to form a palindrome. Thus, answer[0] = false.
 * 	"update 0 b": Update node 0 to 'b', now s = "bbca".
 * 	"query 2 3": Path 2 &rarr; 0 &rarr; 3 gives "cba", which cannot be rearranged
 * to form a palindrome. Thus, answer[1] = false.
 * 	"update 3 a": Update node 3 to 'a', s = "bbca".
 * 	"query 1 3": Path 1 &rarr; 0 &rarr; 3 gives "bba", which can be rearranged to
 * form "bab", a palindrome. Thus, answer[2] = true.
 *
 * Thus, answer = [false, false, true].
 *
 *
 *
 * Constraints:
 *
 * 	1 <= n == s.length <= 5 * 104
 * 	edges.length == n - 1
 * 	edges[i] = [ui, vi]
 * 	0 <= ui, vi <= n - 1
 * 	s consists of lowercase English letters.
 * 	The input is generated such that edges represents a valid tree.
 * 	1 <= queries.length <= 5 * 104​​​​​​​
 *
 * 		queries[i] = "update ui c" or
 * 		queries[i] = "query ui vi"
 * 		0 <= ui, vi <= n - 1
 * 		c is a lowercase English letter.
 *
 * Example 1:
 * Input: n = 3, edges = [[0,1],[1,2]], s = "aac", queries = ["query 0 2","update 1 b","query 0 2"]
 * Output: [true,false]
 *
 * Example 2:
 * Input: n = 4, edges = [[0,1],[0,2],[0,3]], s = "abca", queries = ["query 1 2","update 0 b","query 2 3","update 3 a","query 1 3"]
 * Output: [false,false,true]
 *
 * Constraints:
 * - 1 <= n == s.length <= 5 * 104
 * - edges.length == n - 1
 * - edges[i] = [ui, vi]
 * - 0 <= ui, vi <= n - 1
 * - s consists of lowercase English letters.
 * - The input is generated such that edges represents a valid tree.
 * - 1 <= queries.length <= 5 * 104​​​​​​​
 * - queries[i] = "update ui c" or
 * - queries[i] = "query ui vi"
 * - 0 <= ui, vi <= n - 1
 * - c is a lowercase English letter.
 *
 * Topics: Array, String, Divide and Conquer, Bit Manipulation, Tree, Depth-First Search, Binary Indexed Tree, Segment Tree
 * Time Complexity: O(V + E) or O(n)
 * Space Complexity: O(1) to O(n)
 * Runtime: 0 ms
 * Memory: 42.2 MB
 */

class Solution {
    int[] bit;
    int sz;
    void add(int i, int v) {
        for (i++; i <= sz; i += i & -i) bit[i] ^= v;
    }
    int get(int i) {
        int r = 0;
        for (i++; i > 0; i -= i & -i) r ^= bit[i];
        return r;
    }
    public List<Boolean> palindromePath(int n, int[][] edges, String s, String[] queries) {
        sz = n + 1;
        bit = new int[sz + 1];
        int[] head = new int[n];
        Arrays.fill(head, -1);
        int[] nxt = new int[2 * n];
        int[] to = new int[2 * n];
        int cnt = 0;
        for (int[] e : edges) {
            to[cnt] = e[1]; nxt[cnt] = head[e[0]]; head[e[0]] = cnt++;
            to[cnt] = e[0]; nxt[cnt] = head[e[1]]; head[e[1]] = cnt++;
        }
        char[] c = s.toCharArray();
        int LOG = 16;
        int[][] up = new int[LOG][n];
        int[] dep = new int[n];
        int[] in = new int[n];
        int[] out = new int[n];
        int[] base = new int[n];
        int[] it = new int[n];
        int[] st = new int[n];
        int t = 0, top = 0;
        st[top++] = 0;
        up[0][0] = 0;
        in[0] = t++;
        base[0] = 1 << (c[0] - 'a');
        for (int i = 0; i < n; i++) it[i] = head[i];
        while (top > 0) {
            int u = st[top - 1];
            if (it[u] != -1) {
                int e = it[u];
                it[u] = nxt[e];
                int v = to[e];
                if (v == up[0][u] && u != 0) continue;
                if (u == 0 && v == 0) continue;
                up[0][v] = u;
                dep[v] = dep[u] + 1;
                base[v] = base[u] ^ (1 << (c[v] - 'a'));
                in[v] = t++;
                st[top++] = v;
            } else {
                out[u] = t - 1;
                top--;
            }
        }
        for (int j = 1; j < LOG; j++)
            for (int i = 0; i < n; i++)
                up[j][i] = up[j - 1][up[j - 1][i]];

        List<Boolean> res = new ArrayList<>();
        for (String q : queries) {
            String[] p = q.split(" ");
            if (p[0].charAt(0) == 'u') {
                int u = Integer.parseInt(p[1]);
                char ch = p[2].charAt(0);
                if (c[u] == ch) continue;
                int d = (1 << (c[u] - 'a')) ^ (1 << (ch - 'a'));
                add(in[u], d);
                add(out[u] + 1, d);
                c[u] = ch;
            } else {
                int a = Integer.parseInt(p[1]);
                int b = Integer.parseInt(p[2]);
                int x = a, y = b;
                if (dep[x] < dep[y]) { int tmp = x; x = y; y = tmp; }
                int diff = dep[x] - dep[y];
                for (int j = 0; j < LOG; j++)
                    if (((diff >> j) & 1) == 1) x = up[j][x];
                if (x != y) {
                    for (int j = LOG - 1; j >= 0; j--) {
                        if (up[j][x] != up[j][y]) {
                            x = up[j][x];
                            y = up[j][y];
                        }
                    }
                    x = up[0][x];
                }
                int m = (base[a] ^ get(in[a])) ^ (base[b] ^ get(in[b])) ^ (1 << (c[x] - 'a'));
                res.add((m & (m - 1)) == 0);
            }
        }
        return res;
    }
}
