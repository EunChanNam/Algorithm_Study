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
        int[] a = new int[n];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(st.nextToken());
        }
        int m = Integer.parseInt(br.readLine());

        int[] dp = new int[m + 1];
        for (int i = 1; i <= m; i++) {
            dp[i] = Integer.MAX_VALUE;
        }

        for (int i = 1; i <= m; i++) {
            for (int x = 0; x < n; x++) {
                if (i >= a[x]) {
                    dp[i] = Math.min(dp[i], dp[i - a[x]] + 1);
                }
            }
        }

        int answer = dp[m];
        bw.write(String.valueOf(answer));

        br.close();
        bw.close();
    }
}
