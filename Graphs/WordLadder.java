// LeetCode 127 - Word Ladder
// Time Complexity: O(n * m * 26) | Space Complexity: O(n)
import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class WordLadder {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> words = new HashSet<>(wordList);
        if (!words.contains(endWord)) {
            return 0;
        }

        Queue<String> queue = new ArrayDeque<>();
        queue.offer(beginWord);
        words.remove(beginWord);

        int length = 1;
        while (!queue.isEmpty()) {
            int size = queue.size();

            for (int i = 0; i < size; i++) {
                String word = queue.poll();
                if (endWord.equals(word)) {
                    return length;
                }

                char[] chars = word.toCharArray();
                for (int j = 0; j < chars.length; j++) {
                    char original = chars[j];

                    for (char c = 'a'; c <= 'z'; c++) {
                        if (c == original) {
                            continue;
                        }

                        chars[j] = c;
                        String next = new String(chars);
                        if (words.remove(next)) {
                            queue.offer(next);
                        }
                    }

                    chars[j] = original;
                }
            }

            length++;
        }

        return 0;
    }
}
