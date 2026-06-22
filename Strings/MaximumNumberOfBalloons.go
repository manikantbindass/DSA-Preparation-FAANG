/*
LeetCode Problem 1189: Maximum Number of Balloons
Problem Number: 1189
Difficulty: Easy
Link: https://leetcode.com/problems/maximum-number-of-balloons/

Given a string text, you want to use the characters of text to form as many instances
of the word "balloon" as possible. You can use each character in text at most once.

Return the maximum number of instances that can be formed.

Example 1:
Input: text = "nlaebolko"
Output: 1
Explanation: "nlaebolko" contains 1 "balloon" ("b", "a", "l", "l", "o", "o", "n").

Example 2:
Input: text = "loonbalxballpoon"
Output: 2
Explanation: "loonbalxballpoon" contains 2 "balloon" instances.

Example 3:
Input: text = "leetcode"
Output: 0

Constraints:
- 1 <= text.length <= 10^4
- text consists of lowercase English letters only.

Topics: Hash Table, String, Counting
Time Complexity: O(n) - single pass through the string
Space Complexity: O(1) - using fixed size array of 26
*/

package strings

import "math"

func maxNumberOfBalloons(text string) int {
    charCount := make([]int, 26)
    
    // Count frequency of each character in text
    for i := 0; i < len(text); i++ {
        charCount[text[i]-'a']++
    }
    
    // Calculate maximum possible balloons
    maxBalloons := math.MaxInt32
    
    // Check required characters: b, a, l, o, n
    // We need to check manually since Go doesn't have a simple way to iterate over string with char indices
    required := []struct {
        char byte
        need int
    }{
        {'b', 1},
        {'a', 1},
        {'l', 2},
        {'o', 2},
        {'n', 1},
    }
    
    for _, req := range required {
        count := charCount[req.char-'a']
        possible := count / req.need
        if possible < maxBalloons {
            maxBalloons = possible
        }
    }
    
    return maxBalloons
}
