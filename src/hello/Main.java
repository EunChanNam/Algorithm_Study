package hello;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        if (n >= m) {
            int answer = n - m;
            bw.write(String.valueOf(answer));
            br.close();
            bw.close();
            return;
        }

        int answer = findByBfs(n, m);
        bw.write(String.valueOf(answer));

        br.close();
        bw.close();
    }

    private static class Node {
        int point;
        int dis;

        public Node(int point, int dis) {
            this.point = point;
            this.dis = dis;
        }
    }

    private static int findByBfs(int start, int end) {
//        boolean[] visit = new boolean[1000001];
        int[] record = new int[100001];
        for (int i = 0; i < 100001; i++) {
            record[i] = 1000000;
        }
        record[start] = 0;
//        visit[start] = true;

        Queue<Node> que = new LinkedList<>();
        que.offer(new Node(start, 0));

        while (!que.isEmpty()) {
            Node now = que.poll();

            if (now.point + 1 <= 100000 && now.dis + 1 < record[now.point + 1]) {
                que.offer(new Node(now.point + 1, now.dis + 1));
                record[now.point + 1] = now.dis + 1;
            }
            if (now.point - 1 >= 0 && now.dis + 1 < record[now.point - 1]) {
                que.offer(new Node(now.point - 1, now.dis + 1));
                record[now.point - 1] = now.dis + 1;
            }
            if (now.point * 2 <= 100000 && now.dis < record[now.point * 2]) {
                que.offer(new Node(now.point * 2, now.dis));
                record[now.point * 2] = now.dis;
            }

//            if (now.point * 2 <= 100000 && !visit[now.point * 2]) {
//                que.offer(new Node(now.point * 2, now.dis));
//                visit[now.point * 2] = true;
//            }
//            if (now.point - 1 >= 0 && !visit[now.point - 1]) {
//                que.offer(new Node(now.point - 1, now.dis + 1));
//                visit[now.point - 1] = true;
//            }
//            if (now.point + 1 <= 100000 && !visit[now.point + 1]) {
//                que.offer(new Node(now.point + 1, now.dis + 1));
//                visit[now.point + 1] = true;
//            }
        }

        return record[end];
    }
}
