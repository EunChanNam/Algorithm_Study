package hello;
import java.io.*;
import java.util.*;

public class Main {
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int n;
    static int m;
    static int[] visit;
    static List<List<Node>> list = new ArrayList<>();
    static PriorityQueue<Node> que = new PriorityQueue<>((a, b) -> Integer.compare(a.cost, b.cost));
    static class Node{
        int a; int cost;
        public Node(int a, int cost) {
            this.a = a;
            this.cost = cost;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        visit = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            list.add(new ArrayList<>());
        }
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            list.get(a).add(new Node(b, c));
            list.get(b).add(new Node(a, c));
        }

        int cnt = 0;
        int sum = 0;
        que.add(new Node(1, 0));
        while(cnt < n){
            Node p = que.poll();
            if (visit[p.a] != 1){
                for(Node next : list.get(p.a)){
                    que.offer(next);
                }
                visit[p.a] = 1;
                cnt++;
                sum += p.cost;
            }
        }

        bw.write(String.valueOf(sum));

        br.close();
        bw.close();
    }
}
