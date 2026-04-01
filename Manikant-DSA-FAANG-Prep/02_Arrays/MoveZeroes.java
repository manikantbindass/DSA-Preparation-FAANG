import java.util.Arrays;

/**
 * Move Zeroes
 * LeetCode #283
 * Move all 0s to end, keeping relative order of non-zero elements. In-place.
 *
 * Approach: Two-pointer - slow pointer marks next slot for non-zero element.
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 */
public class MoveZeroes {
    public static void moveZeroes(int[] nums) {
        int insertPos = 0;
        for (int num : nums) {
            if (num != 0) nums[insertPos++] = num;
        }
        while (insertPos < nums.length) {
            nums[insertPos++] = 0;
        }
    }

    public static void main(String[] args) {
        int[] nums1 = {0, 1, 0, 3, 12};
        moveZeroes(nums1);
        System.out.println(Arrays.toString(nums1)); // [1, 3, 12, 0, 0]

        int[] nums2 = {0};
        moveZeroes(nums2);
        System.out.println(Arrays.toString(nums2)); // [0]
    }
}
