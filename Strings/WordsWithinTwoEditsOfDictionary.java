// LeetCode 2452 - Words Within Two Edits of Dictionary
// Time Complexity: O(q * d * k) | Space Complexity: O(1), excluding answer
import java.util.ArrayList;
import java.util.List;

public class WordsWithinTwoEditsOfDictionary {
    public List<String> twoEditWords(String[] queries, String[] dictionary) {
        List<String> answer = new ArrayList<>();
        int wordLength = queries[0].length();

        for (String query : queries) {
            for (String word : dictionary) {
                int differences = 0;
                for (int i = 0; i < wordLength; i++) {
                    if (query.charAt(i) != word.charAt(i)) {
                        differences++;
                    }
                }

                if (differences < 3) {
                    answer.add(query);
                    break;
                }
            }
        }

        return answer;
    }
}
