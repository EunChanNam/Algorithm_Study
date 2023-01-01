package hello;
import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int m;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n + 1][m + 1];
        int[][] dp = new int[n + 1][m + 1];
        for (int y = 1; y <= n; y++) {
            st = new StringTokenizer(br.readLine());
            for (int x = 1; x <= m; x++) {
                map[y][x] = Integer.parseInt(st.nextToken());
            }
        }

        for (int y = 1; y <= n; y++) {
            for (int x = 1; x <= m; x++) {
                dp[y][x] = Math.max(dp[y - 1][x], dp[y][x - 1]);
                dp[y][x] += map[y][x];
            }
        }

        bw.write(String.valueOf(dp[n][m]));

        br.close();
        bw.close();
    }
}
