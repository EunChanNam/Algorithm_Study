package hello;
import java.io.*;
import java.util.*;

public class Main {
    public int solution(int n, int[] money) {
        int answer = 0;

        long[] dp = new long[n + 1];
        dp[0] = 1;
        for (int m : money) {
            for (int i=m; i <= n; i++){
                dp[i] += dp[i - m];
            }
        }

        answer = (int)(dp[n] % 1000000007);

        return answer;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());


        br.close();
        bw.close();
    }
}
