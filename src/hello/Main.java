package hello;
import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static char[][] map;
    static List<Node> s = new ArrayList<>();
    static List<Node> t = new ArrayList<>();
    static Stack<Node> stack = new Stack<>();
    static String answer = "NO";

    static class Node {
        int y; int x;
        public Node(int y, int x){
            this.y = y;
            this.x = x;
        }
    }

    static void dfs(int level, int start) {
        if (level == 3) {
            for (Node p : stack) {
                map[p.y][p.x] = 'O';
            }
            boolean flag = false;
            for (Node p : t) {
                for (int i = p.y + 1; i < n; i++) {
                    if (map[i][p.x] == 'S') {
                        flag = true;
                        break;
                    }
                    if (map[i][p.x] == 'O') break;
                }
                if (flag) break;
                for (int i = p.y - 1; i >= 0; i--) {
                    if (map[i][p.x] == 'S') {
                        flag = true;
                        break;
                    }
                    if (map[i][p.x] == 'O') break;
                }
                if (flag) break;
                for (int i = p.x + 1; i < n; i++) {
                    if (map[p.y][i] == 'S') {
                        flag = true;
                        break;
                    }
                    if (map[p.y][i] == 'O') break;
                }
                if (flag) break;
                for (int i = p.x - 1; i >= 0; i--) {
                    if (map[p.y][i] == 'S') {
                        flag = true;
                        break;
                    }
                    if (map[p.y][i] == 'O') break;
                }
                if (flag) break;
            }
            if (!flag) answer = "YES";
            for (Node p : stack) {
                map[p.y][p.x] = 'X';
            }
            return;
        }

        for (int i = start; i < s.size(); i++) {
            stack.push(s.get(i));
            dfs(level + 1, i + 1);
            stack.pop();
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        map = new char[n][n];
        for (int y = 0; y < n; y++) {
            st = new StringTokenizer(br.readLine());
            for (int x = 0; x < n; x++) {
                char ch = st.nextToken().charAt(0);
                map[y][x] = ch;
                if (ch == 'X') s.add(new Node(y, x));
                if (ch == 'T') t.add(new Node(y, x));
            }
        }

        dfs(0, 0);
        bw.write(answer);

        br.close();
        bw.close();
    }
}
