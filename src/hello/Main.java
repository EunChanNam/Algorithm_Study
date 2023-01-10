package hello;
import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int m;
    static int[][] map;
    static int[][] dir = {{-1, -1}, {1, -1}, {1, 1}, {-1, 1}};
    static int answer = 0;
    static int[][] visit;

    static void dfs(int y, int x, int sum) {
        answer = Math.max(answer, sum);
        if (y >= n) return;
        for (int i = 0; i < 4; i++) {
            int hap = 0;
            int ny = y + dir[i][0];
            int nx = x + dir[i][1];
            if (ny < 0 || nx < 0 || ny >= n || nx >= m) continue;
            if (visit[ny][x] == 1 || visit[y][nx] == 1 || visit[y][x] == 1) continue;
            visit[y][x] = 1;
            visit[ny][x] = 1;
            visit[y][nx] = 1;
            hap += map[y][x] * 2;
            hap += map[ny][x];
            hap += map[y][nx];
            if (x < m - 1) dfs(y, x + 1, sum + hap);
            else dfs(y + 1, 0, sum + hap);
            visit[y][x] = 0;
            visit[ny][x] = 0;
            visit[y][nx] = 0;
        }
        if (x < m - 1) dfs(y, x + 1, sum); // 선택안하는 것도 반드시 넣어줘야함
        else dfs(y + 1, 0, sum);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][m];
        visit = new int[n][m];
        for (int y = 0; y < n; y++) {
            st = new StringTokenizer(br.readLine());
            for (int x = 0; x < m; x++) {
                map[y][x] = Integer.parseInt(st.nextToken());
            }
        }

        if (n * m < 3) {
            bw.write("0");
            br.close();
            bw.close();
            return;
        }

        dfs(0, 0, 0);
        bw.write(String.valueOf(answer));

        br.close();
        bw.close();
    }
}
