package hello;

import java.io.*;
import java.util.*;

public class Main {

    static int[][] dir = {{-1, 0}, {0, -1}, {0, 1}, {1, 0}};

    static int n;
    static int m;
    static int[][] map;

    static class Node {
        int y; int x; int level;

        public Node(int y, int x, int level) {
            this.y = y;
            this.x = x;
            this.level = level;
        }
    }

    static int run(Node start) {
        boolean[][] visit = new boolean[n][m];
        visit[start.y][start.x] = true;

        Queue<Node> que = new LinkedList<>();
        que.offer(start);
        while (!que.isEmpty()) {
            Node p = que.poll();
            if (map[p.y][p.x] == 1) {
                return p.level;
            }

            for (int i = 0; i < 4; i++) {
                int ny = p.y + dir[i][0];
                int nx = p.x + dir[i][1];
                if (ny < 0 || ny >= n || nx < 0 || nx >= m) continue;
                switch (i) {
                    case 0:
                        upLight(que, visit, ny, nx, p.level + 1);
                        break;
                    case 1:
                        leftLight(que, visit, ny, nx, p.level + 1);
                        break;
                    case 2:
                        rightLight(que, visit, ny, nx, p.level + 1);
                        break;
                    case 3:
                        downLight(que, visit, ny, nx, p.level + 1);
                        break;
                }
            }
        }

        return -10;
    }

    static void upLight(Queue<Node> que, boolean[][] visit, int ny, int nx, int level) {
        for (int i = ny; i >= 0; i--) {
            if (visit[i][nx]) continue;
            if (map[i][nx] == -1) break;
            visit[i][nx] = true;
            que.offer(new Node(i, nx, level));
        }
    }

    static void downLight(Queue<Node> que, boolean[][] visit, int ny, int nx, int level) {
        for (int i = ny; i < n; i++) {
            if (visit[i][nx]) continue;
            if (map[i][nx] == -1) break;
            visit[i][nx] = true;
            que.offer(new Node(i, nx, level));
        }
    }

    static void leftLight(Queue<Node> que, boolean[][] visit, int ny, int nx, int level) {
        for (int i = nx; i >= 0; i--) {
            if (visit[ny][i]) continue;
            if (map[ny][i] == -1) break;
            visit[ny][i] = true;
            que.offer(new Node(ny, i, level));
        }
    }

    static void rightLight(Queue<Node> que, boolean[][] visit, int ny, int nx, int level) {
        for (int i = nx; i < m; i++) {
            if (visit[ny][i]) continue;
            if (map[ny][i] == -1) break;
            visit[ny][i] = true;
            que.offer(new Node(ny, i, level));
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        map = new int[n][m];

        Node start = null;

        for (int y = 0; y < n; y++) {
            String input = br.readLine();
            for (int x = 0; x < m; x++) {
                char ch = input.charAt(x);
                if (ch == '.') map[y][x] = 0;
                else if (ch == '*') map[y][x] = -1;
                else if (ch == 'C') {
                    if (start == null) {
                        map[y][x] = -10;
                        start = new Node(y, x, -1);
                    } else {
                        map[y][x] = 1;
                    }
                }
            }
        }

        int result = run(start);

        bw.write(String.valueOf(result));

        br.close();
        bw.close();
    }
}