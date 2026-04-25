// LeetCode 3464 - Maximize the Distance Between Points on a Square
// Time Complexity: O(n log side) | Space Complexity: O(n)
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Deque;
import java.util.List;

public class MaximizeTheDistanceBetweenPointsOnASquare {
    public int maxDistance(int side, int[][] points, int k) {
        List<int[]> ordered = getOrderedPoints(side, points);

        int low = 0;
        int high = side;
        while (low < high) {
            int mid = (low + high + 1) / 2;
            if (isValid(ordered, k, mid)) {
                low = mid;
            } else {
                high = mid - 1;
            }
        }
        return low;
    }

    private boolean isValid(List<int[]> ordered, int k, int d) {
        Deque<Sequence> dq = new ArrayDeque<>();
        int[] first = ordered.get(0);
        dq.offerLast(new Sequence(first[0], first[1], first[0], first[1], 1));

        int maxLen = 1;

        for (int i = 1; i < ordered.size(); i++) {
            int x = ordered.get(i)[0];
            int y = ordered.get(i)[1];

            int startX = x;
            int startY = y;
            int length = 1;

            while (!dq.isEmpty() && distance(x, y, dq.peekFirst().ex, dq.peekFirst().ey) >= d) {
                Sequence curr = dq.pollFirst();

                if (distance(x, y, curr.sx, curr.sy) >= d && curr.len + 1 >= length) {
                    startX = curr.sx;
                    startY = curr.sy;
                    length = curr.len + 1;
                    maxLen = Math.max(maxLen, length);
                }
            }

            dq.offerLast(new Sequence(startX, startY, x, y, length));
        }

        return maxLen >= k;
    }

    private List<int[]> getOrderedPoints(int side, int[][] points) {
        List<int[]> left = new ArrayList<>();
        List<int[]> top = new ArrayList<>();
        List<int[]> right = new ArrayList<>();
        List<int[]> bottom = new ArrayList<>();

        for (int[] point : points) {
            int x = point[0];
            int y = point[1];

            if (x == 0 && y > 0) {
                left.add(point);
            } else if (x > 0 && y == side) {
                top.add(point);
            } else if (x == side && y < side) {
                right.add(point);
            } else {
                bottom.add(point);
            }
        }

        left.sort(Comparator.comparingInt(point -> point[1]));
        top.sort(Comparator.comparingInt(point -> point[0]));
        right.sort((a, b) -> Integer.compare(b[1], a[1]));
        bottom.sort((a, b) -> Integer.compare(b[0], a[0]));

        List<int[]> ordered = new ArrayList<>(points.length);
        ordered.addAll(left);
        ordered.addAll(top);
        ordered.addAll(right);
        ordered.addAll(bottom);
        return ordered;
    }

    private int distance(int x1, int y1, int x2, int y2) {
        return Math.abs(x1 - x2) + Math.abs(y1 - y2);
    }

    private static class Sequence {
        private final int sx;
        private final int sy;
        private final int ex;
        private final int ey;
        private final int len;

        private Sequence(int sx, int sy, int ex, int ey, int len) {
            this.sx = sx;
            this.sy = sy;
            this.ex = ex;
            this.ey = ey;
            this.len = len;
        }
    }
}
