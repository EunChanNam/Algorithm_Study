package hello;
import java.io.*;
import java.util.*;

public class Main {
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int n;
    static int m;
    static int[][] map;
    static List<Point> piz = new ArrayList<>();
    static List<Point> hs = new ArrayList<>();
    static int answer = Integer.MAX_VALUE;
    static Stack<Point> stack = new Stack<>();

    static class Point {
        int y;
        int x;
        public Point(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][n];
        for (int y = 0; y < n; y++) {
            st = new StringTokenizer(br.readLine());
            for (int x = 0; x < n; x++) {
                int a = Integer.parseInt(st.nextToken());
                if (a == 2) {
                    piz.add(new Point(y, x));
                } else if (a == 1) {
                    hs.add(new Point(y, x));
                }
            }
        }

        dfs(0, 0);

        bw.write(String.valueOf(answer));

        br.close();
        bw.close();
    }

    static void dfs(int level, int start) {
        if (level == m) {
            int totalDis = 0;
            for (Point q : hs) {
                int dis = Integer.MAX_VALUE;
                for (Point p : stack) {
                    dis = Math.min(Math.abs(p.y - q.y) + Math.abs(p.x - q.x), dis);
                }
                totalDis += dis;
            }
            answer = Math.min(totalDis, answer);
            return;
        }
        for (int i = start; i < piz.size(); i++) {
            stack.push(piz.get(i));
            dfs(level + 1, i + 1);
            stack.pop();
        }
    }

}