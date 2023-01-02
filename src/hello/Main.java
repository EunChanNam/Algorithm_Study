package hello;
import java.io.*;
import java.util.*;

public class Main {
    static int m;
    static List<List<Node>> list = new ArrayList<>();
    static class Node{
        int k; int val;
        public Node(int k, int val){
            this.k = k;
            this.val = val;
        }
    }
    static int[] visit;
    static int[] ret;
    static void dfs(int now, int sum){
        for (int i=0; i < list.get(now).size(); i++){
            List<Node> nows = list.get(now);
            Node next = nows.get(i);
            if (visit[next.k] == 1) continue;
            if ((sum + next.val) > m) continue;
            visit[next.k] = 1;
            ret[next.k] = 1;
            dfs(next.k, sum + next.val);
            visit[next.k] = 0;
        }
    }
    public int solution(int n, int[][] road, int M) {
        int answer = 0;
        m = M;
        visit = new int[n + 1];
        ret = new int[n + 1];

        for (int i=0; i <= n; i++){
            list.add(new ArrayList<>());
        }

        for (int y=0; y < road.length; y++){
            list.get(road[y][0]).add(new Node(road[y][1], road[y][2]));
            list.get(road[y][1]).add(new Node(road[y][0], road[y][2]));
        }

        visit[1] = 1;
        ret[1] = 1;
        dfs(1, 0);
        for (int i=0; i <= n; i++){
            if (ret[i] == 1) answer++;
        }

        return answer;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;


        br.close();
        bw.close();
    }
}
