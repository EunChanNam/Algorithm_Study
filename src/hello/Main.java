package hello;
import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int[] c;
    static int m;
    static int answer = Integer.MAX_VALUE;
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static void dfs(int cnt, int total) {
        for (int i = n - 1; i >= 0; i--) {
            int nextTotal = total + c[i];
            if (nextTotal > m) continue;
            if (nextTotal == m) {
                Main.answer = Math.min(Main.answer, cnt + 1);
                return;
            }
            dfs(cnt + 1, nextTotal);
            if (i == n - 1) {
                if (nextTotal < m) break;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer st = new StringTokenizer(bf.readLine());

        n = Integer.parseInt(br.readLine());
        c = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            c[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(c);
        m = Integer.parseInt(br.readLine());

        dfs(0, 0);

        bw.write(String.valueOf(answer));

        br.close();
        bw.close();
    }
}