package hello;
import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int m;
    static int[][] map;
    static long[][] dp;
    static int k;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        map = new int[n + 1][m + 1];
        dp = new long[n + 1][m + 1];
        k = Integer.parseInt(br.readLine());
        boolean[][][] road = new boolean[n + 1][m + 1][2];

        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            if (a < c) road[b][a][0] = true;
            if (b < d) road[b][a][1] = true;
            if (c < a) road[d][c][0] = true;
            if (d < b) road[d][c][1] = true;
        }

        dp[0][0] = 1;
        for (int y = 0; y < n + 1; y++) {
            for (int x = 0; x < m + 1; x++) {
                if (y - 1 >= 0 && !road[y - 1][x][1]) {
                    dp[y][x] += dp[y - 1][x];
                }
                if (x - 1 >= 0 && !road[y][x - 1][0]) {
                    dp[y][x] += dp[y][x - 1];
                }
            }
        }

        bw.write(String.valueOf(dp[n][m]));

        br.close();
        bw.close();
    }
}
