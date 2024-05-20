package hello;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int count = 0;
        while (true) {
            count++;
            int n = Integer.parseInt(br.readLine());
            if (n == 0) {
                break;
            }
            int[][] map = new int[n][n];
            for (int y = 0; y < n; y++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int x = 0; x < n; x++) {
                    map[y][x] = Integer.parseInt(st.nextToken());
                }
            }
            solution(n, map, count, bw);
        }

        br.close();
        bw.close();
    }

    private static class Node {
        int y;
        int x;
        int cost;

        public Node(int y, int x, int cost) {
            this.y = y;
            this.x = x;
            this.cost = cost;
        }
    }

    private static int[][] dir = {{0, -1}, {0, 1}, {1, 0}, {-1, 0}};

    private static void solution(int n, int[][] map, int count, BufferedWriter bw) throws IOException {
        int[][] visit = new int[n][n];
        for (int y = 0; y < n; y++) {
            for (int x = 0; x < n; x++) {
                visit[y][x] = Integer.MAX_VALUE;
            }
        }
        visit[0][0] = map[0][0];

        PriorityQueue<Node> que = new PriorityQueue<>((a, b) -> {
            if (a.cost == b.cost) {
                if (a.y == b.y) {
                    return Integer.compare(b.x, a.x);
                }
                return Integer.compare(b.y, a.y);
            }
            return Integer.compare(a.cost, b.cost);
        });
        que.offer(new Node(0, 0, map[0][0]));

        while (!que.isEmpty()) {
            Node now = que.poll();

            boolean finFlag = false;
            for (int i = 0; i < 4; i++) {
                int ny = now.y + dir[i][0];
                int nx = now.x + dir[i][1];

                if (ny >= n || nx >= n || ny < 0 || nx < 0) {
                    continue;
                }
                int nextCost = now.cost + map[ny][nx];
                if (nextCost < visit[ny][nx]) {
                    visit[ny][nx] = nextCost;
                    que.offer(new Node(ny, nx, nextCost));
                    if (ny == n - 1 && nx == n - 1) {
                        finFlag = true;
                        break;
                    }
                }
            }

            if (finFlag) {
                break;
            }
        }

        bw.write("Problem " + count + ": " + visit[n - 1][n - 1]);
        bw.newLine();
    }
}
