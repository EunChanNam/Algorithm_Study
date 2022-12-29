package hello;
import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int m;
    static int[] arr;
    static Stack<Integer> stack = new Stack<>();
    static Set<String> set = new HashSet<>();
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static void dfs(int level, String str, int start) throws IOException {
        if (level == m) {
            if (set.contains(str)) return;
            for (int a : stack) {
                bw.write(a + " ");
            }
            bw.newLine();
            set.add(str);
            return;
        }
        for (int i = start; i < n; i++) {
            stack.push(arr[i]);
            dfs(level + 1, str + arr[i] + " ", i);
            stack.pop();
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        arr = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);

        dfs(0, "", 0);

        br.close();
        bw.close();
    }
}
