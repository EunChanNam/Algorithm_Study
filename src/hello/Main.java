package hello;
import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int m;
    static char[] w;
    static int[] visit;
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static void dfs(int level, int a, int b, String str, int start) throws IOException {
        if (level == n) {
            if (a < 1 || b < 2) return;
            bw.write(str);
            bw.newLine();
            return;
        }
        for (int i = start; i < m; i++) {
            if (w[i] == 'a' || w[i] == 'e' || w[i] == 'i' || w[i] == 'o' || w[i] == 'u') {
                dfs(level + 1, a + 1, b, str + w[i], i + 1);
            } else dfs(level + 1, a, b + 1, str + w[i], i + 1);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        w = new char[m];
        for (int i = 0; i < m; i++) {
            w[i] = st.nextToken().charAt(0);
        }
        Arrays.sort(w);
        visit = new int[m];

        dfs(0, 0, 0, "", 0);

        br.close();
        bw.close();
    }
}
