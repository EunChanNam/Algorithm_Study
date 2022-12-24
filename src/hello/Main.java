package hello;
import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int m;
    static int[][] map;
    static int[][] dir = {{-1, 0}, {0, -1}, {0, 1}, {1, 0}};
    static int[][][] visit;
    static class Point {
        int y; int x; int b; int level;
        public Point(int y, int x, int b, int level){
            this.y = y;
            this.x = x;
            this.b = b;
            this.level = level;
        }
    }
    static int answer = 1000000000;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        visit = new int[2][n][m];
        map = new int[n][m];
        for (int y = 0; y < n; y++) {
            String s = br.readLine();
            for (int x = 0; x < s.length(); x++) {
                char ch = s.charAt(x);
                map[y][x] = ch - '0';
            }
        }

        bfs();
        if (answer == 1000000000) answer = -1;
        bw.write(String.valueOf(answer));

        br.close();
        bw.close();
    }

    static void bfs() {
        Queue<Point> que = new LinkedList<>();
        que.offer(new Point(0, 0, 0, 1));
        visit[0][0][0] = 1;
        visit[1][0][0] = 1;

        while (!que.isEmpty()) {
            Point p = que.poll();
            if (p.y == n - 1 && p.x == m - 1) answer = Math.min(answer, p.level);
            for (int i = 0; i < 4; i++) {
                int ny = p.y + dir[i][0];
                int nx = p.x + dir[i][1];
                if (ny < 0 || nx < 0 || ny >= n || nx >= m) continue;
                if (visit[p.b][ny][nx] == 1) continue;
                if (map[ny][nx] == 1 && p.b == 1) continue;
                if (map[ny][nx] == 1 && p.b == 0) {
                    visit[1][ny][nx] = 1;
                    que.offer(new Point(ny, nx, 1, p.level + 1));
                } else {
                    visit[p.b][ny][nx] = 1;
                    que.offer(new Point(ny, nx, p.b, p.level + 1));
                }
            }
        }
    }
}
