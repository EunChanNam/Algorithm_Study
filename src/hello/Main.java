package hello;
import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int m;
    static int[] a;
    static int max = Integer.MIN_VALUE;
    static Stack<Integer> stack = new Stack<>();

    static void dfs(int level, int start) {
        if (level == 3) {
            int sum = stack.stream().reduce(Integer::sum).get();
            if (sum > m) return;
            max = Math.max(max, sum);
            return;
        }
        for (int i = start; i < n; i++) {
            stack.push(a[i]);
            dfs(level + 1, i + 1);
            stack.pop();
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        a = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(st.nextToken());
        }

        dfs(0, 0);

        bw.write(String.valueOf(max));

        br.close();
        bw.close();
    }
}
