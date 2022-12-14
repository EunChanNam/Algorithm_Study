package hello;
import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static PriorityQueue<Node> que = new PriorityQueue<>((a, b) -> Integer.compare(b.val, a.val));
    static class Node{
        int dis; int val;
        public Node(int dis, int val){
            this.dis = dis;
            this.val = val;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int q = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            que.offer(new Node(q, w));
        }
        st = new StringTokenizer(br.readLine());
        int goal = Integer.parseInt(st.nextToken());
        int start = Integer.parseInt(st.nextToken());

        int cnt = 0;
        int sum = start;
        List<Node> list = new ArrayList<>();
        while (sum < goal && !que.isEmpty()) {
            Node p = que.poll();
            if (sum >= p.dis) {
                sum += p.val;
                cnt++;
                for (Node t : list) {
                    que.offer(t);
                }
                list.clear();
            } else list.add(p);
        }

        if (que.isEmpty()) bw.write(String.valueOf(-1));
        else bw.write(String.valueOf(cnt));

        br.close();
        bw.close();
    }
}
