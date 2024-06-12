import java.util.*;
class Solution {

    private static class Point {
        int y;
        int x;
        public Point(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }

    private int[][] dir = {{-1, 0}, {-1, 1}, {0, 1}, {1, 1}, {1, 0}, {1, -1}, {0, -1}, {-1, -1}};
    public int solution(int[] arrows) {
        int answer = 0;

        Point now = new Point(0, 0);
        Map<String, Integer> map = new HashMap<>();
        map.put(getPoint(now), 0);

        Set<String> set = new HashSet<>();

        for (int arrow : arrows) {
            int ny = now.y + dir[arrow][0];
            int nx = now.x + dir[arrow][1];
            int ny2 = now.y + dir[arrow][0] * 2;
            int nx2 = now.x + dir[arrow][1] * 2;
            Point next = new Point(ny, nx);
            Point next2 = new Point(ny2, nx2);

            String line1 = getLine(now, next);
            String line2 = getLine(next, now);
            String line3 = getLine(next, next2);
            String line4 = getLine(next2, next);

            if (!set.contains(line1) && !set.contains(line2)) {
                String nextPoint = getPoint(next);
                map.put(nextPoint, map.getOrDefault(nextPoint, -1) + 1);
            }
            if (!set.contains(line3) && !set.contains(line4)) {
                String nextPoint = getPoint(next2);
                map.put(nextPoint, map.getOrDefault(nextPoint, -1) + 1);
            }

            set.add(line1);
            set.add(line2);
            set.add(line3);
            set.add(line4);

            now.y = ny2;
            now.x = nx2;
        }

        for (int cnt : map.values()) {
            answer += cnt;
        }

        return answer;
    }

    private String getPoint(Point point) {
        StringBuilder sb = new StringBuilder();
        sb.append(point.y).append("/");
        sb.append(point.x);
        return sb.toString();
    }

    private String getLine(Point a, Point b) {
        StringBuilder sb = new StringBuilder();
        sb.append(a.y).append('/');
        sb.append(a.x).append('/');
        sb.append(b.y).append('/');
        sb.append(b.x);

        return sb.toString();
    }
}
