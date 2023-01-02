package hello;
import java.io.*;
import java.util.*;

public class Main {
    public int solution(int m, int n, int[][] p) {
        int answer = 0;

        int[][] dp = new int[n + 1][m + 1];
        dp[1][1] = 1;

        for (int i=0; i < p.length; i++){
            dp[p[i][1]][p[i][0]] = -1;
        }

        for (int y=1; y <= n; y++){
            for (int x=1; x <= m; x++){
                if (y == 1 && x == 1) continue;
                if (dp[y][x] == -1) continue;
                int up = dp[y - 1][x];
                int down = dp[y][x - 1];
                if (up == -1) up = 0;
                if (down == -1) down = 0;
                dp[y][x] = (up + down) % 1000000007;
            }
        }
        answer = dp[n][m];

        return answer;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;


        br.close();
        bw.close();
    }
}
