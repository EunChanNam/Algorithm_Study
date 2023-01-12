package hello;
import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int m;
    static int[] r;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(br.readLine());
        arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = i;
        }
        for (int y = 0; y < n; y++) {
            st = new StringTokenizer(br.readLine());
            for (int x = 0; x < n; x++) {
                int a = Integer.parseInt(st.nextToken());
                if (a == 1) union(y, x);
                int k = 2;
            }
        }
        r = new int[m];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < m; i++) {
            r[i] = Integer.parseInt(st.nextToken()) - 1;
        }

        boolean suc = true;
        for (int i = 1; i < m; i++) {
            if (find(r[i]) != find(r[i - 1])) {
                suc = false;
                break;
            }
        }
        if (suc) bw.write("YES");
        else bw.write("NO");
        br.close();
        bw.close();
    }

    static int find(int a) {
        if (arr[a] == a) return a;
        else return arr[a] = find(arr[a]);
    }

    static void union(int a, int b) {
        int fa = find(a);
        int fb = find(b);
        if (fa != fb) arr[fa] = fb;
    }
}
