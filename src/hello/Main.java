package hello;
import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int[][] map;
    static int[][] visit;
    static int dy;
    static int dx;
    static int size = 2;
    static int sizeCnt = 0;
    static boolean isMoved;
    static int[][] dir = {{-1, 0}, {0, -1}, {0, 1}, {1, 0}};
    //같은 거리라면 위에, 그리고 왼쪽에 있는 먹이를 먼저 먹으므로 우선순위 큐를 사용해서 큐를 거리(level), y, x 순으로 정렬한다.
    static PriorityQueue<Node> que = new PriorityQueue<>((q, w) -> {
        if (q.level == w.level){
            if (q.y == w.y) return Integer.compare(q.x, w.x);
            else return Integer.compare(q.y, w.y);
        } else return Integer.compare(q.level, w.level);
    });
    static int answer = 0;
    static class Node{
        int y; int x; int level;
        public Node(int y, int x, int level){
            this.y = y;
            this.x = x;
            this.level = level;
        }
    }

    static void bfs() {
        que.offer(new Node(dy, dx, 0));
        visit[dy][dx] = 1;
        while (!que.isEmpty()) {
            Node p = que.poll();
            if (p.level != 0 && map[p.y][p.x] < size && map[p.y][p.x] != 0) {
                map[dy][dx] = 0;
                dy = p.y;
                dx = p.x;
                answer += p.level;
                sizeCnt++;
                if (sizeCnt == size) {
                    size++; sizeCnt = 0;
                }
                isMoved = true;
                que.clear();
                break;
            }
            for (int i = 0; i < 4; i++) {
                int ny = p.y + dir[i][0];
                int nx = p.x + dir[i][1];
                if (ny < 0 || nx < 0 || ny >= n ||nx >= n) continue;
                if (visit[ny][nx] == 1) continue;
                if (map[ny][nx] > size) continue;
                visit[ny][nx] = 1;
                que.offer(new Node(ny, nx, p.level + 1));
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());
        map = new int[n][n];
        for (int y = 0; y < n; y++) {
            st = new StringTokenizer(br.readLine());
            for (int x = 0; x < n; x++) {
                map[y][x] = Integer.parseInt(st.nextToken());
                if (map[y][x] == 9) {
                    dy = y;
                    dx = x;
                }
            }
        }

        boolean keep = true;
        while (keep) {
            visit = new int[n][n];
            isMoved = false;
            bfs();
            if (!isMoved) keep = false;
        }

        bw.write(String.valueOf(answer));

        br.close();
        bw.close();
    }
}
