package hello;
import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int m;
    static int[][] arr;
    static int[] u;
    static List<Integer> real = new ArrayList<>();

    static int find(int a) {
        if (u[a] == a) return a;
        else return u[a] = find(u[a]);
    }

    static boolean union(int a, int b) {
        int fa = find(a);
        int fb = find(b);
        if (fa != fb) {
            u[fa] = fb;
            return false;
        }
        return true;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        u = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            u[i] = i;
        }
        arr = new int[m][];
        st = new StringTokenizer(br.readLine());
        int k = Integer.parseInt(st.nextToken());
        for (int i = 0; i < k; i++) {
            real.add(Integer.parseInt(st.nextToken()));
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int t = Integer.parseInt(st.nextToken());
            arr[i] = new int[t];
            for (int j = 0; j < t; j++) {
                int p = Integer.parseInt(st.nextToken());
                arr[i][j] = p;
            }
        }

        for (int y = 0; y < m; y++) {
            for (int i = 0; i < arr[y].length - 1; i++) {
                for (int j = i + 1; j < arr[y].length; j++) {
                    union(arr[y][i], arr[y][j]);
                }
            }
        }

        int answer = 0;
        for (int y = 0; y < m; y++) {
            boolean flag = true;
            for (int x = 0; x < arr[y].length; x++) {
                for (int p : real) {
                    if (find(p) == find(arr[y][x])) {
                        flag = false;
                        break;
                    }
                }
                if (!flag) break;
            }
            if (flag) answer++;
        }

        bw.write(String.valueOf(answer));

        br.close();
        bw.close();
    }
}
