package hello;
import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int[][] map;
    static Stack<Node> stack = new Stack<>();
    static List<Node> list = new ArrayList<>();
    static int answer = Integer.MAX_VALUE;
    static int[][] dir = {{-1, 0}, {0, -1}, {0, 1}, {1, 0}};

    static class Node{
        int y; int x; int val;
        public Node(int y, int x, int val){
            this.y = y;
            this.x = x;
            this.val = val;
        }
    }
    static void dfs(int level, int start) {
        if (level == 3) {
            int[][] visit = new int[n][n];
            int sum = 0;
            for (Node p : stack) {
                if (visit[p.y][p.x] == 1) return;
                visit[p.y][p.x] = 1;
                sum += map[p.y][p.x];
                for (int i = 0; i < 4; i++) {
                    int ny = p.y + dir[i][0];
                    int nx = p.x + dir[i][1];
                    if (visit[ny][nx] == 1) return;
                    visit[ny][nx] = 1;
                    sum += map[ny][nx];
                }
            }
            answer = Math.min(answer, sum);
            return;
        }
        for (int i = start; i < list.size(); i++) {
            stack.push(list.get(i));
            dfs(level + 1, i + 1);
            stack.pop();
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());
        map = new int[n][n];
        for (int y = 0; y < n; y++) {
            st = new StringTokenizer(br.readLine());
            for (int x = 0; x < n; x++) {
                map[y][x] = Integer.parseInt(st.nextToken());
            }
        }
        for (int y = 1; y < n - 1; y++) {
            for (int x = 1; x < n - 1; x++) {
                list.add(new Node(y, x, map[y][x]));
            }
        }

        dfs(0, 0);

        bw.write(String.valueOf(answer));

        br.close();
        bw.close();
    }
}
