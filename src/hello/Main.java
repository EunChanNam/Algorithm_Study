package hello;
import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int m;
    static int[][] map;
    static int[][] visit;
    static int[][] visit2;
    static int[][] dir = {{-1, 0}, {0, -1}, {0, 1}, {1, 0}};

    static class Node{
        int y; int x; int cnt = 0;
        public Node(int y, int x){
            this.y = y;
            this.x = x;
        }
    }
    static void bfs(int y, int x) {
        Queue<Node> que = new LinkedList<>();
        que.offer(new Node(y, x));
        List<Node> list = new ArrayList<>();

        while (!que.isEmpty()) {
            Node p = que.poll();
            for (int i = 0; i < 4; i++) {
                int ny = p.y + dir[i][0];
                int nx = p.x + dir[i][1];
                if (ny < 0 || nx < 0 || ny >= n || nx >= m) continue;
                if (visit[ny][nx] == 1) continue;
                if (map[ny][nx] == 0) {
                    p.cnt++;
                    continue;
                }
                visit[ny][nx] = 1;
                que.offer(new Node(ny, nx));
            }
            list.add(p);
        }
        for (Node p : list) {
            int val = map[p.y][p.x] - p.cnt;
            if (val < 0) val = 0;
            map[p.y][p.x] = val;
        }
    }

    static void check(int y, int x) {
        Queue<Node> que = new LinkedList<>();
        que.offer(new Node(y, x));

        while (!que.isEmpty()) {
            Node p = que.poll();
            for (int i = 0; i < 4; i++) {
                int ny = p.y + dir[i][0];
                int nx = p.x + dir[i][1];
                if (ny < 0 || nx < 0 || ny >= n || nx >= m) continue;
                if (visit2[ny][nx] == 1) continue;
                if (map[ny][nx] == 0) continue;
                visit2[ny][nx] = 1;
                que.offer(new Node(ny, nx));
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
        map = new int[n][m];
        for (int y = 0; y < n; y++) {
            st = new StringTokenizer(br.readLine());
            for (int x = 0; x < m; x++) {
                map[y][x] = Integer.parseInt(st.nextToken());
            }
        }

        int answer = 0;
        while (true) {
            int rand = 0;
            answer++;
            visit = new int[n][m];
            for (int y = 0; y < n; y++) {
                for (int x = 0; x < m; x++) {
                    if (map[y][x] == 0) continue;
                    if (visit[y][x] == 1) continue;
                    visit[y][x] = 1;
                    bfs(y, x);
                }
            }

            visit2 = new int[n][m];
            for (int y = 0; y < n; y++) {
                for (int x = 0; x < m; x++) {
                    if (map[y][x] == 0) continue;
                    if (visit2[y][x] == 1) continue;
                    visit2[y][x] = 1;
                    check(y, x);
                    rand++;
                }
            }
            if (rand == 0){
                answer = 0;
                break;
            }
            if (rand > 1) {
                break;
            }
        }

        bw.write(String.valueOf(answer));

        br.close();
        bw.close();
    }
}
