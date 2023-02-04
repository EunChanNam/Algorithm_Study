package hello;
import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int[][] map;
    static int[][] visit;
    static int[][] dir = {{-1, 0}, {0, -1}, {0, 1}, {1, 0}};

    static class Node{
        int y; int x; int level;
        public Node(int y, int x, int level){
            this.y = y;
            this.x = x;
            this.level = level;
        }
    }
    static void init(int rand, int y, int x) {
        visit[y][x] = 1;
        Queue<Node> que = new LinkedList<>();
        que.offer(new Node(y, x, 0));
        while (!que.isEmpty()) {
            Node p = que.poll();
            map[p.y][p.x] = rand;
            for (int i = 0; i < 4; i++) {
                int ny = p.y + dir[i][0];
                int nx = p.x + dir[i][1];
                if (ny < 0 || nx < 0 || ny >= n || nx >= n) continue;
                if (visit[ny][nx] == 1) continue;
                if (map[ny][nx] == 1) {
                    visit[ny][nx] = 1;
                    que.offer(new Node(ny, nx, 0));
                }
            }
        }
    }

    static int bfs(int y, int x, int rand) {
        visit = new int[n][n];
        visit[y][x] = 1;
        Queue<Node> que = new LinkedList<>();
        que.offer(new Node(y, x, 0));
        while (!que.isEmpty()) {
            Node p = que.poll();
            if (map[p.y][p.x] != 0 && map[p.y][p.x] != rand) {
                return p.level;
            }
            for (int i = 0; i < 4; i++) {
                int ny = p.y + dir[i][0];
                int nx = p.x + dir[i][1];
                if (ny < 0 || nx < 0 || ny >= n || nx >= n) continue;
                if (visit[ny][nx] == 1) continue;
                if (map[ny][nx] == rand) continue;
                visit[ny][nx] = 1;
                que.offer(new Node(ny, nx, p.level + 1));
            }
        }
        return Integer.MAX_VALUE;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        map = new int[n][n];
        for (int y = 0; y < n; y++) {
            st = new StringTokenizer(br.readLine());
            for (int x = 0; x < n; x++) {
                map[y][x] = Integer.parseInt(st.nextToken());
            }
        }

        int rand = 1;
        visit = new int[n][n];
        for (int y = 0; y < n; y++) {
            for (int x = 0; x < n; x++) {
                if (visit[y][x] == 1) continue;
                if (map[y][x] == 0) continue;
                init(rand, y, x);
                rand++;
            }
        }

        int answer = Integer.MAX_VALUE;
        for (int y = 0; y < n; y++) {
            for (int x = 0; x < n; x++) {
                boolean flag = false;
                if (map[y][x] == 0) continue;
                for (int i = 0; i < 4; i++) {
                    int ny = y + dir[i][0];
                    int nx = x + dir[i][1];
                    if (ny < 0 || nx < 0 || ny >= n || nx >= n) continue;
                    if (map[ny][nx] == 0) {
                        flag = true;
                        break;
                    }
                }
                if (flag) {
                    answer = Math.min(answer, bfs(y, x, map[y][x]));
                }
            }
        }

        bw.write(String.valueOf(answer - 1));

        br.close();
        bw.close();
    }
}
