package hello;
import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int m;
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
            stack.push(i);
            dfs(level + 1, i);
            stack.pop();
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        dfs(0, 1);

        br.close();
        bw.close();
    }
}
