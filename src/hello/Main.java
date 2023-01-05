package hello;
import java.io.*;
import java.util.*;

public class Main {
    static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        int[] coin = {1, 5, 10, 25};
        int[] coins = new int[4];
        for (int i = 0; i < 5; i++) {
            if (i == 0) n = Integer.parseInt(st.nextToken());
            else coins[i - 1] = Integer.parseInt(st.nextToken());
        }

        int[] cnt = new int[n + 1];
        int[] sum = new int[n + 1];
        int[][] dp = new int[4][n + 1];
        for (int i = 1; i <= n; i++) {
            int max = -1;
            int maxIndex = -1;
            int selectedX = -1;
            boolean flag = false;
            for (int x = 0; x < 4; x++) {
                if (coin[x] > i) break;
                int pre = i - coin[x];
                if (dp[x][pre] + 1 > coins[x]) continue;
                if (sum[pre] + coin[x] != i) continue;
                if (cnt[pre] > max){
                    max = cnt[pre];
                    maxIndex = pre;
                    selectedX = x;
                }
                flag = true;
            }
            if (flag) {
                cnt[i] = cnt[maxIndex] + 1;
                sum[i] = sum[maxIndex] + coin[selectedX];
                for (int x = 0; x < 4; x++) {
                    dp[x][i] = dp[x][maxIndex];
                    if (x == selectedX) dp[x][i]++;
                }
            }
        }
        for (int i = 0; i < 4; i++) {
            bw.write(dp[i][n] + " ");
        }

        br.close();
        bw.close();
    }
}
