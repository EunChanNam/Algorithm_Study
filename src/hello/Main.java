package hello;
import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int m;
    static int[][] map;
    static int[] plus;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][n];
        for (int y = 0; y < n; y++) {
            for (int x = 0; x < n; x++) {
                map[y][x] = 1;
            }
        }
        plus = new int[2 * n - 1];
        for (int y = 0; y < m; y++) {
            st = new StringTokenizer(br.readLine());
            int t = 0;
            for (int x = 0; x < 3; x++) {
                int p = Integer.parseInt(st.nextToken());
                for (int k = 0; k < p; k++) {
                    plus[t++] += x;
                }
            }
        }

        int dy = n - 1;
        int dx = 0;
        for (int i = 0; i < 2 * n - 1; i++) {
            map[dy][dx] += plus[i];
            if (dy != 0) dy--;
            else dx++;
        }

        for (int i = 1; i < n; i++) {
            for (int j = 1; j < n; j++) {
                map[i][j] = Math.max(map[i - 1][j], map[i][j]);
                map[i][j] = Math.max(map[i - 1][j - 1], map[i][j]);
                map[i][j] = Math.max(map[i][j - 1], map[i][j]);
            }
        }

        for (int y = 0; y < n; y++) {
            for (int x = 0; x < n; x++) {
                bw.write(map[y][x] + " ");
            }
            bw.newLine();
        }

        br.close();
        bw.close();
    }
}
