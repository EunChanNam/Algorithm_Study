package hello;
import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int m;
    static List<Node> h = new ArrayList<>();
    static List<Node> c = new ArrayList<>();
    static Stack<Node> stack = new Stack<>();
    static int answer = Integer.MAX_VALUE;

    static class Node {
        int y; int x;
        public Node(int y, int x){
            this.y = y;
            this.x = x;
        }
    }

    static void dfs(int level, int start) {
        if (level == m) {
            int sum = 0;
            for (Node a : h) {
                int min = Integer.MAX_VALUE;
                for (Node b : stack) {
                    int dis = Math.abs(a.y - b.y) + Math.abs(a.x - b.x);
                    min = Math.min(min, dis);
                }
                sum += min;
            }
            answer = Math.min(answer, sum);
            return;
        }

        for (int i = start; i < c.size(); i++) {
            stack.push(c.get(i));
            dfs(level + 1, i + 1);
            stack.pop();
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        for (int y = 0; y < n; y++) {
            st = new StringTokenizer(br.readLine());
            for (int x = 0; x < n; x++) {
                int a = Integer.parseInt(st.nextToken());
                if (a == 1) h.add(new Node(y, x));
                if (a == 2) c.add(new Node(y, x));
            }
        }

        dfs(0, 0);
        bw.write(String.valueOf(answer));

        br.close();
        bw.close();
    }
}
