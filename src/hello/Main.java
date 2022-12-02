package hello;
import java.io.*;
import java.util.*;

public class Main {
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int n;
    static List<Node> list = new ArrayList<>();
    static PriorityQueue<Integer> que = new PriorityQueue<>(Collections.reverseOrder());

    static class Node{
        int k; int d;
        public Node(int k, int d) {
            this.k = k;
            this.d = d;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int k = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            list.add(new Node(k, d));
        }
        list.sort((a, b) -> {
            if (a.d == b.d) return Integer.compare(b.k, a.k);
            return Integer.compare(b.d, a.d);
        });

        int i = list.get(0).d;
        int sum =0;
        int x = 0;
        for (int y = i; y >= 1; y--) {
            for ( ; x < n; x++) {
                if (list.get(x).d < y) break;
                que.offer(list.get(x).k);
            }
            if (!que.isEmpty()) sum += que.poll();
        }

        bw.write(String.valueOf(sum));

        br.close();
        bw.close();
    }

}