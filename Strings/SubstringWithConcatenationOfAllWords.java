// LeetCode 30 - Substring with Concatenation of All Words
// Time Complexity: O(k * n) | Space Complexity: O(words.length)
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SubstringWithConcatenationOfAllWords {
    public List<Integer> findSubstring(String s, String[] words) {
        Map<String, Integer> wordCount = new HashMap<>();
        for (String word : words) {
            wordCount.merge(word, 1, Integer::sum);
        }

        List<Integer> answer = new ArrayList<>();
        int stringLength = s.length();
        int wordTotal = words.length;
        int wordLength = words[0].length();

        for (int offset = 0; offset < wordLength; offset++) {
            int left = offset;
            int right = offset;
            Map<String, Integer> windowCount = new HashMap<>();

            while (right + wordLength <= stringLength) {
                String current = s.substring(right, right + wordLength);
                right += wordLength;

                if (!wordCount.containsKey(current)) {
                    windowCount.clear();
                    left = right;
                    continue;
                }

                windowCount.merge(current, 1, Integer::sum);

                while (windowCount.get(current) > wordCount.get(current)) {
                    String removed = s.substring(left, left + wordLength);
                    if (windowCount.merge(removed, -1, Integer::sum) == 0) {
                        windowCount.remove(removed);
                    }
                    left += wordLength;
                }

                if (right - left == wordTotal * wordLength) {
                    answer.add(left);
                }
            }
        }

        return answer;
    }
}
