// LeetCode 67 - Add Binary
// Time Complexity: O(max(m, n)) | Space Complexity: O(max(m, n))
public class AddBinary {
    public String addBinary(String a, String b) {
        StringBuilder answer = new StringBuilder();
        int first = a.length() - 1;
        int second = b.length() - 1;
        int carry = 0;

        while (first >= 0 || second >= 0 || carry > 0) {
            carry += first >= 0 ? a.charAt(first) - '0' : 0;
            carry += second >= 0 ? b.charAt(second) - '0' : 0;
            answer.append(carry % 2);
            carry /= 2;
            first--;
            second--;
        }

        return answer.reverse().toString();
    }
}
