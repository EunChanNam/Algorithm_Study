package hello;

import java.io.*;
import java.util.*;

public class Main {
    static char[][] map = new char[5][5];
    static List<Point> list = new ArrayList<>();
    static int[][] dir = {{-1, 0}, {0, -1}, {0, 1}, {1, 0}};
    static int answer = 0;
    static class Point{
        int y; int x;
        public Point(int y, int x){
            this.y = y;
            this.x = x;
        }
    }

    static Stack<Point> stack = new Stack<>();
    static void run(int level, int start) {
        if (level == 7) {
            if (check()) answer++;
            return;
        }
        for (int i = start; i < 25; i++) {
            stack.push(list.get(i));
            run(level + 1, i + 1);
            stack.pop();
        }
    }
    static boolean check() {
        int[][] temp = new int[5][5];
        int sCnt = 0; int cnt = 0;
        for (Point p : stack) temp[p.y][p.x] = 1;
        Point s = stack.peek();
        int[][] visit = new int[5][5];
        visit[s.y][s.x] = 1;
        Queue<Point> que = new LinkedList<>();
        que.offer(s);

        while (!que.isEmpty()) {
            Point p = que.poll();
            if (temp[p.y][p.x] == 1) cnt++;
            if (map[p.y][p.x] == 'S') {
                sCnt++;
            }
            for (int i = 0; i < 4; i++) {
                int ny = p.y + dir[i][0];
                int nx = p.x + dir[i][1];
                if (ny < 0 || nx < 0 || ny >= 5 || nx >= 5) continue;
                if (visit[ny][nx] == 1) continue;
                visit[ny][nx] = 1;
                if (temp[ny][nx] == 1) que.offer(new Point(ny, nx));
            }
        }
        return cnt == 7 && sCnt >= 4;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
//        st = new StringTokenizer(br.readLine());
//        n = Integer.parseInt(st.nextToken());
//        m = Integer.parseInt(st.nextToken());
        for (int y = 0; y < 5; y++) {
            String str = br.readLine();
            for (int x = 0; x < 5; x++) {
                map[y][x] = str.charAt(x);
                list.add(new Point(y, x));
            }
        }

        run(0, 0);

        bw.write(String.valueOf(answer));

        br.close();
        bw.close();
    }
}
