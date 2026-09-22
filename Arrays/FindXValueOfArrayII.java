/*
 * LeetCode Problem 3840: Find X Value of Array II
 * Problem Number: 3840
 * Difficulty: Hard
 * Link: https://leetcode.com/problems/find-x-value-of-array-ii/
 *
 * You are given an array of positive integers nums and a positive integer k. You
 * are also given a 2D array queries, where queries[i] = [indexi, valuei, starti,
 * xi].
 *
 * You are allowed to perform an operation once on nums, where you can remove any
 * suffix from nums such that nums remains non-empty.
 *
 * The x-value of nums for a given x is defined as the number of ways to perform
 * this operation so that the product of the remaining elements leaves a remainder
 * of x modulo k.
 *
 * For each query in queries you need to determine the x-value of nums for xi after
 * performing the following actions:
 *
 * 	Update nums[indexi] to valuei. Only this step persists for the rest of the
 * queries.
 * 	Remove the prefix nums[0..(starti - 1)] (where nums[0..(-1)] will be used to
 * represent the empty prefix).
 *
 * Return an array result of size queries.length where result[i] is the answer for
 * the ith query.
 *
 * A prefix of an array is a subarray that starts from the beginning of the array
 * and extends to any point within it.
 *
 * A suffix of an array is a subarray that starts at any point within the array and
 * extends to the end of the array.
 *
 * Note that the prefix and suffix to be chosen for the operation can be empty.
 *
 * Note that x-value has a different definition in this version.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,2,3,4,5], k = 3, queries = [[2,2,0,2],[3,3,3,0],[0,1,0,1]]
 *
 * Output: [2,2,2]
 *
 * Explanation:
 *
 * 	For query 0, nums becomes [1, 2, 2, 4, 5], and the empty prefix must be
 * removed. The possible operations are:
 *
 *
 * 		Remove the suffix [2, 4, 5]. nums becomes [1, 2].
 * 		Remove the empty suffix. nums becomes [1, 2, 2, 4, 5] with a product 80, which
 * gives remainder 2 when divided by 3.
 *
 *
 * 	For query 1, nums becomes [1, 2, 2, 3, 5], and the prefix [1, 2, 2] must be
 * removed. The possible operations are:
 *
 * 		Remove the empty suffix. nums becomes [3, 5].
 * 		Remove the suffix [5]. nums becomes [3].
 *
 *
 * 	For query 2, nums becomes [1, 2, 2, 3, 5], and the empty prefix must be
 * removed. The possible operations are:
 *
 * 		Remove the suffix [2, 2, 3, 5]. nums becomes [1].
 * 		Remove the suffix [3, 5]. nums becomes [1, 2, 2].
 *
 *
 *
 * Example 2:
 *
 * Input: nums = [1,2,4,8,16,32], k = 4, queries = [[0,2,0,2],[0,2,0,1]]
 *
 * Output: [1,0]
 *
 * Explanation:
 *
 * 	For query 0, nums becomes [2, 2, 4, 8, 16, 32]. The only possible operation is:
 *
 *
 * 		Remove the suffix [2, 4, 8, 16, 32].
 *
 *
 * 	For query 1, nums becomes [2, 2, 4, 8, 16, 32]. There is no possible way to
 * perform the operation.
 *
 * Example 3:
 *
 * Input: nums = [1,1,2,1,1], k = 2, queries = [[2,1,0,1]]
 *
 * Output: [5]
 *
 *
 *
 * Constraints:
 *
 * 	1 <= nums[i] <= 109
 * 	1 <= nums.length <= 105
 * 	1 <= k <= 5
 * 	1 <= queries.length <= 2 * 104
 * 	queries[i] == [indexi, valuei, starti, xi]
 * 	0 <= indexi <= nums.length - 1
 * 	1 <= valuei <= 109
 * 	0 <= starti <= nums.length - 1
 * 	0 <= xi <= k - 1
 *
 * Example 1:
 * Input: nums = [1,2,3,4,5], k = 3, queries = [[2,2,0,2],[3,3,3,0],[0,1,0,1]]
 * Output: [2,2,2]
 *
 * Example 2:
 * Input: nums = [1,2,4,8,16,32], k = 4, queries = [[0,2,0,2],[0,2,0,1]]
 * Output: [1,0]
 *
 * Example 3:
 * Input: nums = [1,1,2,1,1], k = 2, queries = [[2,1,0,1]]
 * Output: [5]
 *
 * Constraints:
 * - 1 <= nums[i] <= 109
 * - 1 <= nums.length <= 105
 * - 1 <= k <= 5
 * - 1 <= queries.length <= 2 * 104
 * - queries[i] == [indexi, valuei, starti, xi]
 * - 0 <= indexi <= nums.length - 1
 * - 1 <= valuei <= 109
 * - 0 <= starti <= nums.length - 1
 * - 0 <= xi <= k - 1
 *
 * Topics: Array, Math, Segment Tree
 * Time Complexity: O(V + E) or O(n)
 * Space Complexity: O(1) to O(n)
 */

class Node {
    int l, r, prod;
    int[] cnt;
    Node(int l, int r, int k) {
        this.l = l;
        this.r = r;
        this.prod = 1;
        this.cnt = new int[k];
    }
}
class SegmentTree {
    private int k;
    private Node[] tr;
    SegmentTree(int[] nums, int k) {
        this.k = k;
        int n = nums.length;
        tr = new Node[n << 2];
        build(1, 1, n, nums);
    }
    private Node merge(Node a, Node b) {
        Node c = new Node(0, 0, k);
        c.prod = a.prod * b.prod % k;
        System.arraycopy(a.cnt, 0, c.cnt, 0, k);
        for (int r = 0; r < k; ++r) {
            c.cnt[a.prod * r % k] += b.cnt[r];
        }
        return c;
    }
    private void pushup(int u) {
        Node p = merge(tr[u << 1], tr[u << 1 | 1]);
        tr[u].prod = p.prod;
        tr[u].cnt = p.cnt;
    }
    private void build(int u, int l, int r, int[] nums) {
        tr[u] = new Node(l, r, k);
        if (l == r) {
            int v= nums[l - 1] % k;
            tr[u].prod = v;
            tr[u].cnt[v] = 1;
            return;
        }
        int mid = (l + r) >> 1;
        build(u << 1, l, mid, nums);
        build(u << 1 | 1, mid + 1, r, nums);
        pushup(u);
    }
    void modify(int u, int x, int v) {
        if (tr[u].l == tr[u].r) {
            v %= k;
            tr[u].prod = v;
            Arrays.fill(tr[u].cnt, 0);
            tr[u].cnt[v] = 1;
            return;
        }
        int mid = (tr[u].l + tr[u].r) >> 1;
        if (x <= mid) {
            modify(u << 1, x, v);
        } else {
            modify(u << 1 | 1, x, v);
        }
        pushup(u);
    }
    Node query(int u, int l, int r) {
        if (tr[u].l >= l && tr[u].r <= r) {
            return tr[u];
        }
        int mid = (tr[u].l + tr[u].r) >> 1;
        if (r <= mid) {
            return query(u << 1, l, r);
        }
        if (l > mid) {
            return query(u << 1 | 1, l, r);
        }
        return merge(query(u << 1, l, r), query(u << 1 | 1, l, r));
    }
}
class Solution {
    public int[] resultArray(int[] nums, int k, int[][] queries) {
        int n = nums.length;
        SegmentTree tree = new SegmentTree(nums, k);
        int[] ans = new int[queries.length];
        for (int i = 0; i < queries.length; ++i) {
            int idx = queries[i][0], val = queries[i][1], start = queries[i][2], x = queries[i][3];
            tree.modify(1, idx + 1, val);
            ans[i] = tree.query(1, start + 1, n).cnt[x];
        }
        return ans;
    }
}
