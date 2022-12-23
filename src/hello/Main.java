package hello;
import java.io.*;
import java.util.*;

public class Main {
    static int[][] dir = {{-1, 0}, {0, -1}, {0, 1}, {1, 0}};
    static List<List<Character>> map = new ArrayList<>();
    static class Node{
        int y; int x; int level;
        public Node(int y, int x, int level){
            this.y = y;
            this.x = x;
            this.level = level;
        }
    }
    static int cnt = 0;
    static int[][] visit;
    static List<Node> rmList;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        for (int i = 0; i < 6; i++) {
            map.add(new ArrayList<>());
        }
        for (int y = 0; y < 12; y++) {
            String s = br.readLine();
            for (int x = 0; x < 6; x++) {
                char ch = s.charAt(x);
                map.get(x).add(0, ch);
            }
        }

        boolean stop = true;
        while (stop) {
            boolean flag = false;
            visit = new int[12][12];
            rmList = new ArrayList<>();
            for (int y = 0; y < map.size(); y++) {
                for (int x = 0; x < map.get(y).size(); x++) {
                    if (visit[y][x] == 1) continue;
                    if (map.get(y).get(x) == '.') continue;
                    boolean bfs = bfs(new Node(y, x, 0), map.get(y).get(x));
                    if (bfs) {
                        flag = true;
                    }
                }
            }
            rmList.sort((a, b) -> Integer.compare(b.x, a.x));
            for (Node p : rmList) {
                map.get(p.y).remove(p.x);
            }
            if (flag) cnt++;
            if (!flag) stop = false;
        }

        bw.write(String.valueOf(cnt));

        br.close();
        bw.close();
    }

    static boolean bfs(Node first, char target) {
        Queue<Node> que = new LinkedList<>();
        List<Node> list = new ArrayList<>();
        que.offer(first);
        visit[first.y][first.x] = 1;
        while (!que.isEmpty()) {
            Node p = que.poll();
            list.add(p);
            for (int i = 0; i < 4; i++) {
                int ny = p.y + dir[i][0];
                int nx = p.x + dir[i][1];
                if (ny < 0 || nx < 0 || ny >= 6 || nx >= 12) continue;
                if (visit[ny][nx] == 1) continue;
                if (map.get(ny).size() <= nx) continue;
                if (map.get(ny).get(nx) != target) continue;
                visit[ny][nx] = 1;
                que.offer(new Node(ny, nx, p.level + 1));
            }
        }
        if (list.size() >= 4){
            rmList.addAll(list);
            return true;
        }
        else return false;
    }
}
