package hello;

import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int m;
    static class Node{
        int k; int level;
        public Node(int k, int level){
            this.k = k;
            this.level = level;
        }
    }

    static int cnt = 0;
    static int goalLevel;
    static void bfs(int start) {
        int[] visit = new int[200001];
        visit[start] = 1;
        goalLevel = Integer.MAX_VALUE;
        Queue<Node> que = new LinkedList<>();
        que.offer(new Node(start, 0));

        while (!que.isEmpty()) {
            Node p = que.poll();
            if (p.level > goalLevel) continue;
            if (p.k == m) {
                goalLevel = p.level;
                cnt++;
                continue;
            }
            if (p.k > 0 && (visit[p.k - 1] == 0 || visit[p.k - 1] == p.level)) {
                visit[p.k - 1] = p.level;
                que.offer(new Node(p.k - 1, p.level + 1));
            }
            if (p.k < 200000 && (visit[p.k + 1] == 0 || visit[p.k + 1] == p.level)) {
                visit[p.k + 1] = p.level;
                que.offer(new Node(p.k + 1, p.level + 1));
            }
            if (p.k <= 100000 && (visit[p.k * 2] == 0 || visit[p.k * 2] == p.level)) {
                visit[p.k * 2] = p.level;
                que.offer(new Node(p.k * 2, p.level + 1));
            }
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        bfs(n);

        bw.write(String.valueOf(goalLevel));
        bw.newLine();
        bw.write(String.valueOf(cnt));

        br.close();
        bw.close();
    }
}
