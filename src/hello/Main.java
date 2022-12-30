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
        int[][] dp = new int[2][n + 1];
        int[] arr = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
        dp[0][1] = arr[1];

        int answer = 0;
        for (int i = 2; i <= n; i++) {
            //선택
            int max = Math.max(arr[i - 1] + dp[1][i - 2], dp[1][i - 1]); //이전 선택 vs 이전 선택 X
            dp[0][i] = max + arr[i];
            //노 선택
            max = Math.max(dp[0][i - 1], dp[1][i - 1]);
            dp[1][i] = max;

            answer = Math.max(answer, dp[0][i]);
            answer = Math.max(answer, dp[1][i]);
        }
        if (n == 1) answer = arr[1];

        bw.write(String.valueOf(answer));

        br.close();
        bw.close();
    }
}
