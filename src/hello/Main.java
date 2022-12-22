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
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        int[] plus = {1, 2, 3};
        int[] dp = new int[1000001];
        dp[0] = 1;

        for (int i = 1; i < 1000001; i++) {
            for (int p : plus) {
                if (i >= p) {
                    dp[i] = (dp[i] + dp[i - p]) % 1000000009;
                }
            }
        }

        for (int i = 0; i < n; i++) {
            bw.write(String.valueOf(dp[arr[i]]));
            if (i != n - 1) bw.newLine();
        }

        br.close();
        bw.close();
    }
}
