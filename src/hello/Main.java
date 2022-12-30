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
        int[] arr = new int[n + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 1; i <= n; i++) {
            for (int x = i; x >= 1; x--) {
                int temp = Math.abs(arr[i] - arr[x]) + dp[x - 1];
                dp[i] = Math.max(temp, dp[i]);
            }
        }

        bw.write(String.valueOf(dp[n]));

        br.close();
        bw.close();
    }
}
