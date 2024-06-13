import java.util.*;
class Solution {

    private List<List<Integer>> list = new ArrayList<>();

    public boolean validPath(int n, int[][] edges, int source, int destination) {
        for (int i=0; i < n; i++) {
            list.add(new ArrayList<>());
        }

        for (int[] edge : edges) {
            int a = edge[0];
            int b = edge[1];
            list.get(a).add(b);
            list.get(b).add(a);
        }

        return findByBfs(source, destination, n);
    }

    private boolean findByBfs(int start, int goal, int n) {
        boolean[] visit = new boolean[n];
        visit[start] = true;

        Queue<Integer> que = new ArrayDeque<>();
        que.offer(start);

        while (!que.isEmpty()) {
            int now = que.poll();
            if (now == goal) return true;

            List<Integer> nowList = list.get(now);
            for (int next : nowList) {
                if (visit[next]) continue;
                visit[next] = true;
                que.offer(next);
            }
        }

        return false;
    }
}
