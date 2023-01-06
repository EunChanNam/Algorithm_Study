package hello;
import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int m;
    static int[][] map;
    static List<Node> a = new ArrayList<>();
    static List<Node> b = new ArrayList<>();
    static Stack<Node> stack = new Stack<>();
    static int[][] dir = {{-1, 0}, {0, -1}, {0, 1}, {1, 0}};
    static int[][] visit;
    static int total = 0;
    static int cnt;
    static int answer = 0;

    static class Node{
        int y; int x;
        public Node(int y, int x){
            this.y = y;
            this.x = x;
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
                int k = Integer.parseInt(st.nextToken());
                if (k == 0) {
                    a.add(new Node(y, x));
                    total++;
                }
                if (k == 2) b.add(new Node(y, x));
                map[y][x] = k;
            }
        }

        dfs(0, 0);
        bw.write(String.valueOf(answer));

        br.close();
        bw.close();
    }

    static void dfs(int level, int start) {
        if (level == 3) {
            for (Node p : stack) {
                map[p.y][p.x] = 1;
            }
            cnt = 0;
            visit = new int[n][m];
            for (Node p : b) {
                if (visit[p.y][p.x] != 1) bfs(p);
            }
            for (Node p : stack) {
                map[p.y][p.x] = 0;
            }
            answer = Math.max(answer, total - cnt - 3);
            return;
        }
        for (int i = start; i < a.size(); i++) {
            stack.push(a.get(i));
            dfs(level + 1, i + 1);
            stack.pop();
        }
    }

    static void bfs(Node start) {
        Queue<Node> que = new LinkedList<>();
        visit[start.y][start.x] = 1;
        que.offer(start);

        while (!que.isEmpty()) {
            Node p = que.poll();
            if (map[p.y][p.x] == 0) cnt++;
            for (int i = 0; i < 4; i++) {
                int ny = p.y + dir[i][0];
                int nx = p.x + dir[i][1];
                if (ny < 0 || nx < 0 || ny >= n || nx >= m) continue;
                if (visit[ny][nx] == 1) continue;
                if (map[ny][nx] == 1) continue;
                visit[ny][nx] = 1;
                que.offer(new Node(ny, nx));
            }
        }
    }
}
