package hello;

import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int[][] map = new int[11][11];
    static int[] visit;

    static int answer;
    static void run(int level, int sum) {
        if (level == 11) {
            answer = Math.max(answer, sum);
            return;
        }

        for (int i = 0; i < n; i++) {
            if (visit[i] == 1) continue;
            if (map[level][i] == 0) continue;
            visit[i] = 1;
            run(level + 1, sum + map[level][i]);
            visit[i] = 0;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        n = 11;
        int k = Integer.parseInt(br.readLine());
        for (int i=0; i < k; i++){
            for (int y = 0; y < n; y++) {
                st = new StringTokenizer(br.readLine());
                for (int x = 0; x < n; x++) {
                    map[y][x] = Integer.parseInt(st.nextToken());
                }
            }
            answer = Integer.MIN_VALUE;
            visit = new int[11];
            run(0, 0);
            bw.write(String.valueOf(answer));
            bw.newLine();
        }

        br.close();
        bw.close();
    }
}
