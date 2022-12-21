package hello;
import java.io.*;
import java.util.*;

public class Main {
    static int n;

    static class Node{
        int val; int t;
        public Node(int val, int t){
            this.val = val;
            this.t = t;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());
        int[] dp = new int[end + 1];
        List<Node> list = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            list.add(new Node(a, b));
        }

        int answer = 0;
        for (Node p : list) {
            for (int i = end; i >= p.t; i--) {
                dp[i] = Math.max(dp[i], dp[i - p.t] + p.val);
            }
        }
        answer = dp[end];

        bw.write(String.valueOf(answer));

        br.close();
        bw.close();
    }
}
