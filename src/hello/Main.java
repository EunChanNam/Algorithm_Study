import java.util.*;
class Solution {

    private int[] uf;

    public int solution(int n, int[][] costs) {
        int answer = 0;

        uf = new int[n];
        for (int i=0; i < n; i++) {
            uf[i] = i;
        }

        Arrays.sort(costs, (a, b) -> Integer.compare(a[2], b[2]));

        for (int i=0; i < costs.length; i++) {
            int[] now = costs[i];
            int a = now[0];
            int b = now[1];
            int cost = now[2];

            if (union(a, b)) {
                answer += cost;
            }
        }

        return answer;
    }

    private int find(int a) {
        if (uf[a] == a) return a;
        return uf[a] = find(uf[a]);
    }

    private boolean union(int a, int b) {
        int fa = find(a);
        int fb = find(b);
        if (fa != fb) {
            uf[fa] = fb;
            return true;
        }
        return false;
    }
}
