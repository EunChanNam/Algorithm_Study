package hello;
import java.io.*;
import java.time.Year;
import java.util.*;

public class Main {
    static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        int[] dp = new int[n + 1];

        dp[0] = 1;
        for (int i = 2; i <= n; i += 2) {
            dp[i] = dp[i - 2] * 3;
            for (int x = 4; x <= n; x += 2) {
                int a = i - x;
                if (a < 0) break;
                dp[i] += dp[a] * 2;
            }
        }
        Year
        bw.write(String.valueOf(dp[n]));

        br.close();
        bw.close();
    }
}
