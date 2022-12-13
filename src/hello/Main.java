package hello;
import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int m;
    static int[][] map;
    static int[][] dir = {{1, 0}, {-1, 0}, {0, -1}, {0, 1}};
    static int max = Integer.MIN_VALUE;
    static int[][] visit;

    static void dfs(int level, int sum, int y, int x) {
        if (level == 4) {
            max = Math.max(max, sum);
            return;
        }
        for (int i = 0; i < 4; i++) {
            int ny = y + dir[i][0];
            int nx = x + dir[i][1];
            if (ny < 0 || nx < 0 || ny >= n || nx >= m) continue;
            if (visit[ny][nx] == 1) continue;
            visit[ny][nx] = 1;
            dfs(level + 1, sum + map[ny][nx], ny, nx);
            visit[ny][nx] = 0;
        }
    }

    static void processT(int y, int x) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            int ny = y + dir[i][0];
            int nx = x + dir[i][1];
            if (ny < 0 || nx < 0 || ny >= n || nx >= m) continue;
            list.add(map[ny][nx]);
        }
        if (list.size() < 3) return;
        else if (list.size() == 3) {
            int sum = list.stream().reduce(Integer::sum).get();
            max = Math.max(max, sum + map[y][x]);
        } else {
            int sum = list.stream().reduce(Integer::sum).get();
            for (int i = 0; i < 4; i++) {
                max = Math.max(max, sum - list.get(i) + map[y][x]);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][m];
        for (int y = 0; y < n; y++) {
            st = new StringTokenizer(br.readLine());
            for (int x = 0; x < m; x++) {
                map[y][x] = Integer.parseInt(st.nextToken());
            }
        }
        visit = new int[n][m];

        for (int y = 0; y < n; y++) {
            for (int x=0; x < m; x++){
                visit[y][x] = 1;
                dfs(1, map[y][x], y, x);
                visit[y][x] = 0;
                processT(y, x);
            }
        }

        bw.write(String.valueOf(max));

        br.close();
        bw.close();
    }
}
