/*
 * LeetCode Problem 315: Count of Smaller Numbers After Self
 * Problem Number: 315
 * Difficulty: Hard
 * Link: https://leetcode.com/problems/count-of-smaller-numbers-after-self/
 *
 * Given an integer array nums, return an integer array counts where counts[i] is
 * the number of smaller elements to the right of nums[i].
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [5,2,6,1]
 * Output: [2,1,1,0]
 * Explanation:
 * To the right of 5 there are 2 smaller elements (2 and 1).
 * To the right of 2 there is only 1 smaller element (1).
 * To the right of 6 there is 1 smaller element (1).
 * To the right of 1 there is 0 smaller element.
 *
 * Example 2:
 *
 * Input: nums = [-1]
 * Output: [0]
 *
 * Example 3:
 *
 * Input: nums = [-1,-1]
 * Output: [0,0]
 *
 *
 *
 * Constraints:
 *
 * 	1 <= nums.length <= 105
 * 	-104 <= nums[i] <= 104
 *
 * Example 1:
 * Input: nums = [5,2,6,1]
 * Output: [2,1,1,0]
 *
 * Example 2:
 * Input: nums = [-1]
 * Output: [0]
 *
 * Example 3:
 * Input: nums = [-1,-1]
 * Output: [0,0]
 *
 * Constraints:
 * - 1 <= nums.length <= 105
 * - -104 <= nums[i] <= 104
 *
 * Topics: Array, Binary Search, Divide and Conquer, Binary Indexed Tree, Segment Tree, Merge Sort, Ordered Set, Treap
 * Time Complexity: O(n log n)
 * Space Complexity: O(n)
 * Approach: Scan from right to left and use a Fenwick tree over compressed values to count strictly smaller elements.
 */

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

class Solution {
    public List<Integer> countSmaller(int[] nums) {
        Set<Integer> s = new HashSet<>();
        for (int v : nums) {
            s.add(v);
        }
        List<Integer> alls = new ArrayList<>(s);
        alls.sort(Comparator.comparingInt(a -> a));
        int n = alls.size();
        Map<Integer, Integer> m = new HashMap<>(n);
        for (int i = 0; i < n; ++i) {
            m.put(alls.get(i), i + 1);
        }
        BinaryIndexedTree tree = new BinaryIndexedTree(n);
        LinkedList<Integer> ans = new LinkedList<>();
        for (int i = nums.length - 1; i >= 0; --i) {
            int x = m.get(nums[i]);
            tree.update(x, 1);
            ans.addFirst(tree.query(x - 1));
        }
        return ans;
    }
}
class BinaryIndexedTree {
    private int n;
    private int[] c;
    public BinaryIndexedTree(int n) {
        this.n = n;
        c = new int[n + 1];
    }
    public void update(int x, int delta) {
        while (x <= n) {
            c[x] += delta;
            x += lowbit(x);
        }
    }
    public int query(int x) {
        int s = 0;
        while (x > 0) {
            s += c[x];
            x -= lowbit(x);
        }
        return s;
    }
    public static int lowbit(int x) {
        return x & -x;
    }
}
