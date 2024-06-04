import java.util.*;
class Solution {

    private class Cost {
        int a;
        int b;
        int cost;
        public Cost(int a, int b, int cost) {
            this.a = a;
            this.b = b;
            this.cost = cost;
        }
    }

    private int[] uf;

    public int solution(int n, int[][] arrs) {
        int answer = 0;

        uf = new int[n];
        for (int i=0; i < n; i++) {
            uf[i] = i;
        }

        List<Cost> costs = new ArrayList<>();
        for (int[] arr : arrs) {
            costs.add(new Cost(arr[0], arr[1], arr[2]));
        }

        costs.sort(Comparator.comparingInt(a -> a.cost));

        int totalCost = 0;
        for (Cost cost : costs) {
            int a = cost.a;
            int b = cost.b;
            if (find(a) != find(b)){
                union(a, b);
                totalCost += cost.cost;
            }
        }

        answer = totalCost;

        return answer;
    }

    private int find(int a) {
        if (uf[a] == a) return uf[a];
        return uf[a] = find(uf[a]);
    }

    private void union(int a, int b) {
        int fa = find(a);
        int fb = find(b);
        if (fa != fb) {
            uf[fa] = fb;
        }
    }
}
