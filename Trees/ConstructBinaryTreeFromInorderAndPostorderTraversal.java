// LeetCode 106 - Construct Binary Tree from Inorder and Postorder Traversal
// Time Complexity: O(n) | Space Complexity: O(n)
import java.util.HashMap;
import java.util.Map;

public class ConstructBinaryTreeFromInorderAndPostorderTraversal {
    private final Map<Integer, Integer> inorderIndex = new HashMap<>();
    private int[] postorder;

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        this.postorder = postorder;
        int length = inorder.length;

        for (int index = 0; index < length; index++) {
            inorderIndex.put(inorder[index], index);
        }

        return dfs(0, 0, length);
    }

    private TreeNode dfs(int inorderStart, int postorderStart, int size) {
        if (size <= 0) {
            return null;
        }

        int rootValue = postorder[postorderStart + size - 1];
        int splitIndex = inorderIndex.get(rootValue);
        TreeNode left = dfs(inorderStart, postorderStart, splitIndex - inorderStart);
        TreeNode right = dfs(
            splitIndex + 1,
            postorderStart + splitIndex - inorderStart,
            size - splitIndex + inorderStart - 1
        );

        return new TreeNode(rootValue, left, right);
    }
}
