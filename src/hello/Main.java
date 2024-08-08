package hello;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    private static int[][] oddDir = {{0, -1}, {1, -1}, {-1, 0}, {1, 0}, {0, 1}, {1, 1}};
    private static int[][] evenDir = {{-1, -1}, {0, -1}, {-1, 0}, {1, 0}, {-1, 1}, {0, 1}};
    private static int[][] map;
    private static int n;
    private static int m;

    private static class Node {

        int y;
        int x;
        int level;

        public Node(int y, int x, int level) {
            this.y = y;
            this.x = x;
            this.level = level;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());

        map = new int[n][m];
        for (int y = 0; y < n; y++) {
            st = new StringTokenizer(br.readLine());
            for (int x = 0; x < m; x++) {
                map[y][x] = Integer.parseInt(st.nextToken());
            }
        }

        int answer = 0;
        boolean[][] visit = new boolean[n][m];
        for (int y = 0; y < n; y++) {
            for (int x = 0; x < m; x++) {
                if (visit[y][x]) {
                    continue;
                }
                if (map[y][x] == 0 && isOutLine(y, x)) {
                    answer += getWall(visit, true, y, x);
                } else if (map[y][x] == 1) {
                    answer += getWall(visit, false, y, x);
                }
            }
        }

        bw.write(String.valueOf(answer));

        br.close();
        bw.close();
    }

    private static int getWall(boolean[][] visit, boolean isWhite, int y, int x) {
        visit[y][x] = true;

        Node start = new Node(y, x, 0);
        Queue<Node> que = new ArrayDeque<>();
        que.offer(start);

        int cnt = 0;
        while (!que.isEmpty()) {
            Node now = que.poll();

            for (int i = 0; i < 6; i++) {
                int ny = getNy(now.y, i);
                int nx = getNx(now.y, now.x, i);

                if (ny < 0 || nx < 0 || ny >= n || nx >= m) {
                    if (!isWhite) {
                        cnt++;
                    }
                    continue;
                }

                Node next = new Node(ny, nx, now.level + 1);
                if (isWhite && map[ny][nx] == 1) cnt++;
                if (visit[ny][nx]) continue;
                if (isWhite && map[ny][nx] == 0) {
                    visit[ny][nx] = true;
                    que.offer(next);
                }
                if (!isWhite && map[ny][nx] == 1) {
                    visit[ny][nx] = true;
                    que.offer(next);
                }
            }
        }

        return cnt;
    }

    private static boolean isOutLine(int y, int x) {
        boolean[][] visit = new boolean[n][m];
        visit[y][x] = true;

        Node start = new Node(y, x, 0);
        Queue<Node> que = new ArrayDeque<>();
        que.offer(start);

        while (!que.isEmpty()) {
            Node now = que.poll();

            for (int i = 0; i < 6; i++) {
                int ny = getNy(now.y, i);
                int nx = getNx(now.y, now.x, i);
                if (ny < 0 || nx < 0 || ny >= n || nx >= m) {
                    return true;
                }
                if (visit[ny][nx]) {
                    continue;
                }
                if (map[ny][nx] == 1) continue;
                visit[ny][nx] = true;
                que.offer(new Node(ny, nx, 0));
            }
        }
        return false;
    }

    private static int getNy(int y, int i) {
        if (y % 2 == 1) {
            return y + evenDir[i][1];
        }
        return y + oddDir[i][1];
    }

    private static int getNx(int y, int x, int i) {
        if (y % 2 == 1) {
            return x + evenDir[i][0];
        }
        return x + oddDir[i][0];
    }
}
