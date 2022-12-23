package hello;
import java.io.*;
import java.util.*;

public class Main {
    static int n;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[][] dp = new int[2][m + 1];
        for (int i = 0; i < m + 1; i++) {
            dp[0][i] = 200000;
        }

        int[] coin = new int[n];
        for (int i = 0; i < n; i++) {
            coin[i] = Integer.parseInt(br.readLine());
        }

        dp[1][0] = 1;
        dp[0][0] = 0;
        for (int c : coin) {
            for (int i = c; i < m + 1; i++) {
                dp[0][i] = Math.min(dp[0][i], dp[0][i - c] + 1);
                dp[1][i] += dp[1][i - c];
                int a =3;
            }
        }

        if (dp[1][m] == 0) bw.write("-1");
        else bw.write(String.valueOf(dp[0][m]));

        br.close();
        bw.close();
    }
}
