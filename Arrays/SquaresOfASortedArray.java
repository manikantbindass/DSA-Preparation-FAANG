// LeetCode 977 - Squares of a Sorted Array
// Time Complexity: O(n) | Space Complexity: O(n)
public class SquaresOfASortedArray {
    public int[] sortedSquares(int[] nums) {
        int length = nums.length;
        int[] answer = new int[length];
        int left = 0;
        int right = length - 1;

        for (int index = length - 1; index >= 0; index--) {
            int leftSquare = nums[left] * nums[left];
            int rightSquare = nums[right] * nums[right];

            if (leftSquare > rightSquare) {
                answer[index] = leftSquare;
                left++;
            } else {
                answer[index] = rightSquare;
                right--;
            }
        }

        return answer;
    }
}
