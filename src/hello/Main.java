package hello;
import java.io.*;
import java.util.*;

public class Main {
    //BFS를 도착지 기준으로 돌리면 모든 시작점으로 부터의 최소거리를 한번에 구할 수 있다.
    static int n;
    static Map<Integer, Integer> map = new HashMap<>();
    static class Node{
        int k; int level;
        public Node(int k, int level){
            this.k = k;
            this.level = level;
        }
    }
    static List<List<Integer>> list = new ArrayList<>();
    public int[] solution(int m, int[][] r, int[] s, int d) {
        n = m;
        int[] answer = new int [s.length];

        for (int i=0; i <= n; i++){
            list.add(new ArrayList<>());
        }
        for (int i=0; i < r.length; i++){
            list.get(r[i][0]).add(r[i][1]);
            list.get(r[i][1]).add(r[i][0]);
        }

        int[] dis = bfs(d);
        for (int i=0; i < s.length; i++){
            int t = dis[s[i]];
            if (t == 0 && s[i] != d) t = -1;
            answer[i] = t;
        }
        return answer;
    }
    static int[] bfs(int end){
        Queue<Node> que = new LinkedList<>();
        int[] dis = new int[n + 1];
        int[] visit = new int[n + 1];
        visit[end] = 1;
        que.offer(new Node(end, 0));

        while(!que.isEmpty()){
            Node p = que.poll();
            dis[p.k] = p.level;
            for (int i=0; i < list.get(p.k).size(); i++){
                int next = list.get(p.k).get(i);
                if (visit[next] == 1) continue;
                visit[next] = 1;
                que.offer(new Node(next, p.level + 1));
            }
        }
        return dis;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        br.close();
        bw.close();
    }
}
