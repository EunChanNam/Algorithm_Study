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
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        int m = Integer.parseInt(br.readLine());
        String[] dp = new String[m + 1];
        for (int i = 0; i < m + 1; i++) {
            dp[i] = "";
        }

        for (int i = 1; i < m + 1; i++) {
            for (int x = 0; x < n; x++) {
                if (arr[x] > i) continue;
                if (dp[i - arr[x]].isEmpty()) {
                    dp[i] = String.valueOf(x);
                    continue;
                }
                String a = String.valueOf(x);
                String big;
                String temp1 = dp[i - arr[x]] + a;
                String temp2 = a + dp[i - arr[x]];
                if (a.equals("0")) {
                    if (temp1.charAt(0) == '0') big = "0";
                    else big = temp1;
                }
                else if (temp1.length() > temp2.length()) big = temp1;
                else if (temp2.length() > temp1.length()) big = temp2;
                else {
                    if (temp1.compareTo(temp2) < 0) big = temp2;
                    else big = temp1;
                }
                if (dp[i].isEmpty()) dp[i] = big;
                else {
                    if (big.length() > dp[i].length()) dp[i] = big;
                    else if (big.length() == dp[i].length()){
                        if (big.compareTo(dp[i]) > 0) dp[i] = big;
                    }
                }
            }
        }

        bw.write(dp[m]);

        br.close();
        bw.close();
    }
}
