import java.util.*;
class Solution {

    private static class Route {
        int start;
        int end;
        public Route(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    List<Route> routes;

    public int solution(int[][] arrs) {
        int answer = 0;

        routes = new ArrayList<>();
        for (int[] arr : arrs) {
            routes.add(new Route(arr[0], arr[1]));
        }

        routes.sort((a, b) -> Integer.compare(a.end, b.end));

        for (int i=0; i < routes.size();) {
            Route now = routes.get(i);

            i = updateCheckPoint(i, now.end);
            answer++;
        }

        return answer;
    }

    private int updateCheckPoint(int checkPoint, int end) {
        for (int i=checkPoint; i < routes.size(); i++) {
            Route now = routes.get(i);
            if (now.start > end || now.end < end) {
                return i;
            }
        }
        return routes.size();
    }
}
