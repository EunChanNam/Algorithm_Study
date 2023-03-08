package hello;

import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int m;
    static int[][] map;
    static List<Node> fire;
    static int[][] dir = {{-1, 0}, {0, -1}, {0, 1}, {1, 0}};

    static class Node{
        int y; int x; int level;
        public Node (int y, int x, int level){
            this.y = y;
            this.x = x;
            this.level = level;
        }
    }

    static int answer = -1;
    static void bfs(Node start) {
        int now = -1;
        int[][] visit = new int[n][m];
        visit[start.y][start.x] = 1;
        Queue<Node> que = new LinkedList<>();
        que.offer(start);

        while (!que.isEmpty()) {
            Node p = que.poll();
            if (p.level > now) {
                now = p.level;
                List<Node> temp = new ArrayList<>();
                for (Node f : fire) {
                    for (int i = 0; i < 4; i++) {
                        int ny = f.y + dir[i][0];
                        int nx = f.x + dir[i][1];
                        if (ny < 0 || nx < 0 || ny >= n || nx >= m) continue;
                        if (map[ny][nx] == 1) {
                            map[ny][nx] = -1;
                            temp.add(new Node(ny, nx, 0));
                        }
                    }
                }
                fire = new ArrayList<>(temp);
            }
            for (int i = 0; i < 4; i++) {
                int ny = p.y + dir[i][0];
                int nx = p.x + dir[i][1];
                if (ny < 0 || nx < 0 || ny >= n || nx >= m) {
                    answer = p.level;
                    return;
                }
            }
            for (int i = 0; i < 4; i++) {
                int ny = p.y + dir[i][0];
                int nx = p.x + dir[i][1];
                if (ny < 0 || nx < 0 || ny >= n || nx >= m) continue;
                if (visit[ny][nx] == 1) continue;
                if (map[ny][nx] == 1){
                    visit[ny][nx] = 1;
                    que.offer(new Node(ny, nx, p.level + 1));
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int k = Integer.parseInt(br.readLine());

        for (int i=0; i < k; i++){
            answer = -1;
            StringTokenizer st = new StringTokenizer(br.readLine());
            m = Integer.parseInt(st.nextToken());
            n = Integer.parseInt(st.nextToken());
            map = new int[n][m];
            fire = new ArrayList<>();
            Node start = null;
            for (int y = 0; y < n; y++) {
                String s = br.readLine();
                for (int x = 0; x < m; x++) {
                    char ch = s.charAt(x);
                    if (ch == '#') map[y][x] = 0;
                    if (ch == '.') map[y][x] = 1;
                    if (ch == '@') {
                        map[y][x] = 2;
                        start = new Node(y, x, 0);
                    }
                    if (ch == '*') {
                        map[y][x] = -1;
                        fire.add(new Node(y, x, 0));
                    }
                }
            }

            bfs(start);

            if (answer == -1) bw.write("IMPOSSIBLE");
            else bw.write(String.valueOf(answer + 1));
            bw.newLine();
        }
        br.close();
        bw.close();
    }
}