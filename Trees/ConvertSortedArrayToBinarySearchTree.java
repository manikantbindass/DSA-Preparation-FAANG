// LeetCode 108 - Convert Sorted Array to Binary Search Tree
// Time Complexity: O(n) | Space Complexity: O(log n)
public class ConvertSortedArrayToBinarySearchTree {
    private int[] nums;

    public TreeNode sortedArrayToBST(int[] nums) {
        this.nums = nums;
        return dfs(0, nums.length - 1);
    }

    private TreeNode dfs(int left, int right) {
        if (left > right) {
            return null;
        }

        int mid = (left + right) >> 1;
        return new TreeNode(nums[mid], dfs(left, mid - 1), dfs(mid + 1, right));
    }
}
