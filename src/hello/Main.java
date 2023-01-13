package hello;
import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int m;
    static int[][] map;
    static int[][] dir = {{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1}};
    static class Node{
        int y; int x; int level;
        public Node(int y, int x, int level){
            this.y = y;
            this.x = x;
            this.level = level;
        }
    }

    static int bfs(Node start) {
        Queue<Node> que = new LinkedList<>();
        int[][] visit = new int[n][m];
        visit[start.y][start.x] = 1;
        que.offer(start);

        while (!que.isEmpty()) {
            Node p = que.poll();
            if (map[p.y][p.x] == 1) return p.level;
            for (int i = 0; i < 8; i++) {
                int ny = p.y + dir[i][0];
                int nx = p.x + dir[i][1];
                if (ny < 0 || nx < 0 || ny >= n || nx >= m) continue;
                if (visit[ny][nx] == 1) continue;
                visit[ny][nx] = 1;
                que.offer(new Node(ny, nx, p.level + 1));
            }
        }
        return 0;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][m];
        for (int y = 0; y < n; y++) {
            st = new StringTokenizer(br.readLine());
            for (int x = 0; x < m; x++) {
                map[y][x] = Integer.parseInt(st.nextToken());
            }
        }

        int answer = 0;
        for (int y = 0; y < n; y++) {
            for (int x = 0; x < m; x++) {
                if (map[y][x] == 0) {
                    answer = Math.max(answer, bfs(new Node(y, x, 0)));
                }
            }
        }

        bw.write(String.valueOf(answer));

        br.close();
        bw.close();
    }
}
