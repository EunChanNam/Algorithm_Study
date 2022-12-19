package hello;
import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static class Node{
        int s; int h; int w;
        public Node(int s, int h, int w){
            this.s = s;
            this.h = h;
            this.w = w;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());
        Node[] a = new Node[n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int h = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            a[i] = new Node(s, h, w);
        }
        Arrays.sort(a, (q, w) -> Integer.compare(w.s, q.s));

        int[] dp = new int[n];
        dp[0] = a[0].h;

        int answer = dp[0];
        for (int i = 1; i < n; i++) {
            for (int j = i - 1; j >= 0; j--) {
                if (a[j].w > a[i].w) {
                    dp[i] = Math.max(dp[i], dp[j]);
                }
            }
            dp[i] += a[i].h;
            answer = Math.max(answer, dp[i]);
        }

        bw.write(String.valueOf(answer));

        br.close();
        bw.close();
    }
}
