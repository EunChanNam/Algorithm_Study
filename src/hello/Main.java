package hello;
import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int k;
    static String[] s;
    static int[] dat = new int[150];
    static int answer = 0;

    static void dfs(int level, int start) {
        if (level == k) {
            int cnt = 0;
            for (int i = 0; i < n; i++) {
                boolean flag = true;
                for (char ch : s[i].toCharArray()) {
                    if (dat[ch] == 0) {
                        flag = false;
                        break;
                    }
                }
                if (flag) cnt++;
            }
            answer = Math.max(answer, cnt);
            return;
        }
        for (int i = start; i <= 'z'; i++) {
            if (dat[i] == 1) continue;
            dat[i] = 1;
            dfs(level + 1, i + 1);
            dat[i] = 0;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        s = new String[n];
        for (int i = 0; i < n; i++) {
            String str = br.readLine();
            if (str.equals("antatica")) s[i] = "";
            else s[i] = str;
        }
        if (k < 5) {
            bw.write("0");
            br.close();
            bw.close();
            return;
        }
        k -= 5;
        dat['a'] = 1; dat['c'] = 1; dat['i'] = 1; dat['n'] = 1; dat['t'] = 1;

        dfs(0, 'a');
        bw.write(String.valueOf(answer));

        br.close();
        bw.close();
    }
}
