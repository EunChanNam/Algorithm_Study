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
        double[] arr = new double[n];

        for (int i = 0; i < n; i++) {
            arr[i] = Double.parseDouble(br.readLine());
        }
        double[][] dp = new double[2][n];

        dp[0][0] = arr[0];
        dp[1][0] = arr[0];

        double answer = 0;
        for (int i = 1; i < n; i++) {
            double a = dp[0][i - 1];
            double b = dp[1][i - 1];
            dp[0][i] = Math.max(a, b) * arr[i];
            dp[1][i] = arr[i];
            answer = Math.max(answer, dp[0][i]);
            answer = Math.max(answer, dp[1][i]);
        }

        bw.write(String.format("%.3f", answer));

        br.close();
        bw.close();
    }
}
