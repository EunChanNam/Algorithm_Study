package hello;
import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int m;
    static int[][] map;
    static List<Node> b = new ArrayList<>();
    static int goal = 0;
    static Stack<Node> stack = new Stack<>();
    static int[][] dir = {{-1, 0}, {0, -1}, {0, 1}, {1, 0}};
    static int answer = 1000000000;

    static class Node{
        int y; int x; int level;
        public Node(int y, int  x, int level){
            this.y = y;
            this.x = x;
            this.level = level;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][n];

        for (int y = 0; y < n; y++) {
            st = new StringTokenizer(br.readLine());
            for (int x = 0; x < n; x++) {
                map[y][x] = Integer.parseInt(st.nextToken());
                if (map[y][x] == 0 || map[y][x] == 2) goal++;
                if (map[y][x] == 2) b.add(new Node(y, x, 0));
            }
        }

        dfs(0, 0);

        if (answer == 1000000000) answer = -1;
        bw.write(String.valueOf(answer));

        br.close();
        bw.close();
    }

    static int bfs() {
        int[][] visit = new int[n][n];
        Queue<Node> que = new LinkedList<>();
        for (Node p : stack) {
            visit[p.y][p.x] = 1;
            que.offer(p);
        }

        int cnt = 0;
        while (!que.isEmpty()) {
            Node p = que.poll();
            if (map[p.y][p.x] == 0 || map[p.y][p.x] == 2) cnt++;
            if (cnt == goal) return p.level;
            for (int i = 0; i < 4; i++) {
                int ny = p.y + dir[i][0];
                int nx = p.x + dir[i][1];
                if (ny < 0 || nx < 0 || ny >= n || nx >= n) continue;
                if (visit[ny][nx] == 1) continue;
                if (map[ny][nx] == 1) continue;
                visit[ny][nx] = 1;
                que.offer(new Node(ny, nx, p.level + 1));
            }
        }
        return -1;
    }

    static void dfs(int level, int start) {
        if (level == m){
            int ret = bfs();
            if (ret != -1) {
                answer = Math.min(answer, ret);
            }
            return;
        }
        for (int i = start; i < b.size(); i++) {
            stack.push(b.get(i));
            dfs(level + 1, i + 1);
            stack.pop();
        }
    }
}
