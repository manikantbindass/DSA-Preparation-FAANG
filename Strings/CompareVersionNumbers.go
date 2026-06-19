/*
LeetCode Problem 165: Compare Version Numbers
Problem Number: 165
Difficulty: Medium
Link: https://leetcode.com/problems/compare-version-numbers/

Given two version strings, version1 and version2, compare them.

Version strings consist of revisions separated by dots '.'. A revision is an integer
and may contain leading zeros. To compare version strings, compare their revision
values in left-to-right order. If one version has more revisions than the other,
treat the missing revisions as 0.

Return the following:
- If version1 < version2, return -1.
- If version1 > version2, return 1.
- Otherwise, return 0.

Example 1:
Input: version1 = "1.01", version2 = "1.001"
Output: 0
Explanation: Ignoring leading zeros, both versions represent the same value.

Example 2:
Input: version1 = "1.0", version2 = "1.0.0"
Output: 0
Explanation: version1 has fewer revisions, but missing revisions are treated as 0.

Example 3:
Input: version1 = "0.1", version2 = "1.1"
Output: -1

Constraints:
- 1 <= version1.length, version2.length <= 500
- version1 and version2 consist of digits and '.' only.
- version1 and version2 are valid version numbers.

Topics: Two Pointers, String
Time Complexity: O(n + m) - where n and m are the lengths of the two strings
Space Complexity: O(1) - only using constant extra space
*/

package strings

func compareVersion(version1 string, version2 string) int {
    i, j := 0, 0
    n, m := len(version1), len(version2)
    
    for i < n || j < m {
        num1, num2 := 0, 0
        
        // Parse next revision from version1
        for i < n && version1[i] != '.' {
            num1 = num1*10 + int(version1[i]-'0')
            i++
        }
        
        // Parse next revision from version2
        for j < m && version2[j] != '.' {
            num2 = num2*10 + int(version2[j]-'0')
            j++
        }
        
        // Compare revisions
        if num1 < num2 {
            return -1
        }
        if num1 > num2 {
            return 1
        }
        
        // Move past the dot (if any)
        i++
        j++
    }
    
    return 0
}
