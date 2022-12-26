package hello;
import java.io.*;
import java.util.*;

public class Main {
    static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int goal = arr[n - 1];
        long[][] dp = new long[n][21];

        dp[0][arr[0]] = 1;

        for (int y = 1; y < n - 1; y++) {
            for (int x = 0; x <= 20; x++) {
                if (dp[y - 1][x] != 0) {
                    int plus = x + arr[y];
                    int minus = x - arr[y];
                    if (plus <= 20) {
                        dp[y][plus] += dp[y - 1][x];
                    }
                    if (minus >= 0) {
                        dp[y][minus] += dp[y - 1][x];
                    }
                }
            }
        }

        bw.write(String.valueOf(dp[n - 2][goal]));


        br.close();
        bw.close();
    }
}
