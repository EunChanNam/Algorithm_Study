package hello;
import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int m;
    static int[] visit;
    static Stack<Integer> stack = new Stack<>();
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static void dfs(int level, int start) throws IOException {
        if (level == m) {
            for (int a : stack) {
                bw.write(a + " ");
            }
            bw.newLine();
            return;
        }
        for (int i = start; i <= n; i++) {
            if (visit[i] == 1) continue;
            visit[i] = 1;
            stack.push(i);
            dfs(level + 1, i + 1);
            stack.pop();
            visit[i] = 0;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        visit = new int[n + 1];

        dfs(0, 1);

        br.close();
        bw.close();
    }
}
