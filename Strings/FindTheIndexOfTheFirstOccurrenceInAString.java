// LeetCode 28 - Find the Index of the First Occurrence in a String
// Time Complexity: O(n * m) | Space Complexity: O(1)
public class FindTheIndexOfTheFirstOccurrenceInAString {
    public int strStr(String haystack, String needle) {
        if ("".equals(needle)) {
            return 0;
        }

        int haystackLength = haystack.length();
        int needleLength = needle.length();
        int haystackIndex = 0;
        int needleIndex = 0;

        while (haystackIndex < haystackLength) {
            if (haystack.charAt(haystackIndex) == needle.charAt(needleIndex)) {
                if (needleLength == 1) {
                    return haystackIndex;
                }
                haystackIndex++;
                needleIndex++;
            } else {
                haystackIndex -= needleIndex - 1;
                needleIndex = 0;
            }

            if (needleIndex == needleLength) {
                return haystackIndex - needleIndex;
            }
        }

        return -1;
    }
}
