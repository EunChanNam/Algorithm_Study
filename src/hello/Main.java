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

        for (int i = 0; i < n; i++) {
            int a = Integer.parseInt(br.readLine());
            int b = Integer.parseInt(br.readLine());
            int[][] dp = new int[1000][b + 1];
            for (int k = 0; k < b + 1; k++) {
                dp[0][k] = k;
            }
            for (int y = 1; y < a + 1; y++) {
                for (int x = 1; x < b + 1; x++) {
                    dp[y][x] = dp[y][x - 1] + dp[y - 1][x];
                }
            }
            bw.write(String.valueOf(dp[a][b]));
            bw.newLine();
        }


        br.close();
        bw.close();
    }
}
