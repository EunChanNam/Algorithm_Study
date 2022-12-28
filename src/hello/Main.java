package hello;
import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        String str1 = br.readLine();
        String str2 = br.readLine();
        int n1 = str1.length();
        int n2 = str2.length();
        int[][] dp = new int[n1 + 1][n2 + 1];
//        DP 배열을 2차원으로 구현하고 Y는 첫 번째 문자열, X는 두 번째 문자열로 설정하고
//        각 배열의 값은 해당 문자열까지 비교하며 얻을 수 있는 최대 LCS이다.
//        배열을 탐색하며 비교하는 문자가 같으면 해당 Y는 해당 X에서 사용해야 하므로 dp[y][x] = dp[y - 1][x - 1] + 1 이다
//        같지 않으면 Y에서 이전의 문자를 비교할때의 최대 혹은 X에서 이전의 문자를 비교할 때의 최대 중에 큰 값이다.
//        즉 dp[y][x] = Math.max(dp[y][x - 1], dp[y - 1][x])


        for (int y = 1; y <= n1; y++) {
            for (int x = 1; x <= n2; x++) {
                if (str2.charAt(x - 1) == str1.charAt(y - 1)) {
                    dp[y][x] = dp[y - 1][x - 1] + 1;
                } else dp[y][x] = Math.max(dp[y][x - 1], dp[y - 1][x]);
            }
        }

        bw.write(String.valueOf(dp[n1][n2]));

        br.close();
        bw.close();
    }
}
