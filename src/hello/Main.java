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
        if (total > m) return;
        if (total == m) {
            answer = Math.min(answer, cnt);
        }
        if (cnt > answer) return;
        for (int i = n - 1; i >= 0; i--) {
            dfs(cnt + 1, total + c[i]);
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