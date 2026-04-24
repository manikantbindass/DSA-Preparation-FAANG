// LeetCode 2833 - Furthest Point From Origin
// Time Complexity: O(n) | Space Complexity: O(1)
public class FurthestPointFromOrigin {
    public int furthestDistanceFromOrigin(String moves) {
        return Math.abs(count(moves, 'L') - count(moves, 'R')) + count(moves, '_');
    }

    private int count(String s, char target) {
        int total = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == target) {
                total++;
            }
        }
        return total;
    }
}
