package hello;
import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int m;
    static int[][] map;
    static int[][] visit;
    static int[][] dir = {{-1, 0}, {0, -1}, {0, 1}, {1, 0}};
    static class Point {
        int y; int x;
        public Point(int y, int x){
            this.y = y;
            this.x = x;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            map = new int[m][n];
            int k = Integer.parseInt(st.nextToken());
            for (int j = 0; j < k; j++) {
                st = new StringTokenizer(br.readLine());
                int dx = Integer.parseInt(st.nextToken());
                int dy = Integer.parseInt(st.nextToken());
                map[dy][dx] = 1;
            }

            int cnt = 0;
            visit = new int[m][n];
            for (int y = 0; y < m; y++) {
                for (int x = 0; x < n; x++) {
                    if (map[y][x] == 1) {
                        if (visit[y][x] == 1) continue;
                        visit[y][x] = 1;
                        bfs(y, x);
                        cnt++;
                    }
                }
            }
            bw.write(String.valueOf(cnt));
            if (i != T - 1) bw.newLine();
        }

        br.close();
        bw.close();
    }

    static void bfs(int y, int x) {
        Queue<Point> que = new LinkedList<>();
        que.offer(new Point(y, x));

        while (!que.isEmpty()) {
            Point p = que.poll();
            for (int i = 0; i < 4; i++) {
                int ny = p.y + dir[i][0];
                int nx = p.x + dir[i][1];
                if (ny < 0 || nx < 0 || ny >= m || nx >= n) continue;
                if (visit[ny][nx] == 1) continue;
                if (map[ny][nx] == 0) continue;
                visit[ny][nx] = 1;
                que.offer(new Point(ny, nx));
            }
        }
    }
}
