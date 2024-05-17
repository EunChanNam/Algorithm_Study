package hello;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        arr = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
        visit = new boolean[n + 1];
        cycle = new boolean[n + 1];

        for (int i = 1; i <= n; i++) {
            if (cycle[i]) {
                continue;
            }
            stack.push(i);
            dfs(i, i);
            stack.pop();
        }

        int cnt = 0;
        for (int i = 1; i <= n; i++) {
            if (cycle[i]) {
                cnt++;
            }
        }

        bw.write(String.valueOf(cnt));
        for (int i = 1; i <= n; i++) {
            if (cycle[i]) {
                bw.newLine();
                bw.write(String.valueOf(i));
            }
        }

        br.close();
        bw.close();
    }

    private static int[] arr;
    private static boolean[] visit;
    private static boolean[] cycle;
    private static Stack<Integer> stack = new Stack<>();

    private static void dfs(int start, int target) {
        if (arr[start] == target) {
            stack.forEach(idx -> cycle[idx] = true);
            return;
        }

        if (!visit[arr[start]] && !cycle[arr[start]]) {
            visit[arr[start]] = true;
            stack.push(arr[start]);
            dfs(arr[start], target);
            stack.pop();
            visit[arr[start]] = false;
        }
    }
}
