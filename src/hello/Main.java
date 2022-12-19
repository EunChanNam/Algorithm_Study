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

        int answer = 1;
        int[] dp = new int[n];
        dp[0] = 1;
        for (int i = 1; i < n; i++) {
            for (int x = i - 1; x >= 0; x--) {
                if (a[x] < a[i]) {
                    dp[i] = Math.max(dp[i], dp[x]);
                }
            }
            dp[i]++;
            answer = Math.max(answer, dp[i]);
        }

        bw.write(String.valueOf(answer));

        br.close();
        bw.close();
    }
}
