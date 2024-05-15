package hello;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    private static class Node {

        int target;
        int cost;

        public Node(int target, int cost) {
            this.target = target;
            this.cost = cost;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        List<List<Node>> map = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            map.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            map.get(a).add(new Node(b, cost));
            map.get(b).add(new Node(a, cost));
        }

        int[] dis = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            dis[i] = Integer.MAX_VALUE;
        }
        dis[1] = 0;

        PriorityQueue<Node> que = new PriorityQueue<>(Comparator.comparingInt(a -> a.cost));
        que.offer(new Node(1, 0));

        while (!que.isEmpty()) {
            Node now = que.poll();

            List<Node> nowMap = map.get(now.target);
            for (Node node : nowMap) {
                int nextCost = now.cost + node.cost;
                if (nextCost < dis[node.target]) {
                    dis[node.target] = nextCost;
                    que.offer(new Node(node.target, nextCost));
                }
            }
        }

        bw.write(String.valueOf(dis[n]));

        br.close();
        bw.close();
    }
}
