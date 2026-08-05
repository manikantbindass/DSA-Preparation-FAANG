/*
 * LeetCode Problem 3561: Remove Methods From Project
 * Problem Number: 3561
 * Difficulty: Medium
 * Link: https://leetcode.com/problems/remove-methods-from-project/
 *
 * You are maintaining a project that has n methods numbered from 0 to n - 1.
 *
 * You are given two integers n and k, and a 2D integer array invocations, where
 * invocations[i] = [ai, bi] indicates that method ai invokes method bi.
 *
 * There is a known bug in method k. Method k, along with any method invoked by it,
 * either directly or indirectly, are considered suspicious and we aim to remove
 * them.
 *
 * A group of methods can only be removed if no method outside the group invokes
 * any methods within it.
 *
 * Return an array containing all the remaining methods after removing all the
 * suspicious methods. You may return the answer in any order. If it is not
 * possible to remove all the suspicious methods, none should be removed.
 *
 *
 *
 * Example 1:
 *
 * Input: n = 4, k = 1, invocations = [[1,2],[0,1],[3,2]]
 *
 * Output: [0,1,2,3]
 *
 * Explanation:
 *
 * Method 2 and method 1 are suspicious, but they are directly invoked by methods 3
 * and 0, which are not suspicious. We return all elements without removing
 * anything.
 *
 * Example 2:
 *
 * Input: n = 5, k = 0, invocations = [[1,2],[0,2],[0,1],[3,4]]
 *
 * Output: [3,4]
 *
 * Explanation:
 *
 * Methods 0, 1, and 2 are suspicious and they are not directly invoked by any
 * other method. We can remove them.
 *
 * Example 3:
 *
 * Input: n = 3, k = 2, invocations = [[1,2],[0,1],[2,0]]
 *
 * Output: []
 *
 * Explanation:
 *
 * All methods are suspicious. We can remove them.
 *
 *
 *
 * Constraints:
 *
 * 	1 <= n <= 105
 * 	0 <= k <= n - 1
 * 	0 <= invocations.length <= 2 * 105
 * 	invocations[i] == [ai, bi]
 * 	0 <= ai, bi <= n - 1
 * 	ai != bi
 * 	invocations[i] != invocations[j]
 *
 * Example 1:
 * Input: n = 4, k = 1, invocations = [[1,2],[0,1],[3,2]]
 * Output: [0,1,2,3]
 *
 * Example 2:
 * Input: n = 5, k = 0, invocations = [[1,2],[0,2],[0,1],[3,4]]
 * Output: [3,4]
 *
 * Example 3:
 * Input: n = 3, k = 2, invocations = [[1,2],[0,1],[2,0]]
 * Output: []
 *
 * Constraints:
 * - 1 <= n <= 105
 * - 0 <= k <= n - 1
 * - 0 <= invocations.length <= 2 * 105
 * - invocations[i] == [ai, bi]
 * - 0 <= ai, bi <= n - 1
 * - ai != bi
 * - invocations[i] != invocations[j]
 *
 * Topics: Depth-First Search, Breadth-First Search, Graph Theory
 * Time Complexity: O(V + E) or O(n)
 * Space Complexity: O(1) to O(n)
 * Runtime: 1 ms
 * Memory: 42.7 MB
 */

class Solution {
    private boolean[] suspicious;
    private boolean[] vis;
    private List<Integer>[] f;
    private List<Integer>[] g;
    public List<Integer> remainingMethods(int n, int k, int[][] invocations) {
        suspicious = new boolean[n];
        vis = new boolean[n];
        f = new List[n];
        g = new List[n];
        Arrays.setAll(f, i->new ArrayList<>());
        Arrays.setAll(g,i-> new ArrayList<>());
        for (var e:invocations) {
            int a= e[0],b =e[1];
            f[a].add(b);
            f[b].add(a);
            g[a].add(b);
        }
        dfs(k);
        for (int i=0; i<n;++i) {
            if (!suspicious[i] && !vis[i]) {
                dfs2(i);
            }
        }
        List<Integer> ans=new ArrayList<>();
        for (int i =0; i<n;++i) {
            if (!suspicious[i]) {
                ans.add(i);
            }
        }
        return ans;
    }
    private void dfs(int i) {
        suspicious[i]=true;
        for (int j :g[i]) {
            if (!suspicious[j]) {
                dfs(j);
            }
        }
    }
    private void dfs2(int i) {
        vis[i] =true;
        for (int j: f[i]){
            if (!vis[j]) {
                suspicious[j]=false;
                dfs2(j);
            }
        }
    }
}
