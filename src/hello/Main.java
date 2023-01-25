package hello;
import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int m;
    static List<List<Integer>> list1 = new ArrayList<>();
    static List<List<Integer>> list2 = new ArrayList<>();
    static int[] check;

    static void bfs(List<List<Integer>> list, int start){
        Queue<Integer> que = new LinkedList<>();
        que.offer(start);
        int[] visit = new int[n + 1];
        visit[start] = 1;
        while(!que.isEmpty()){
            int now = que.poll();
            check[now] = 1;
            for (int next : list.get(now)){
                if (visit[next] == 1) continue;
                visit[next] = 1;
                que.offer(next);
            }
        }
    }
    public int solution(int n3, int[][] arr) {
        int answer = 0;
        n = n3;
        m = arr.length;
        for (int i=0; i <= n; i++){
            list1.add(new ArrayList<>());
            list2.add(new ArrayList<>());
        }

        for (int i=0; i < m; i++){
            list1.get(arr[i][0]).add(arr[i][1]);
            list2.get(arr[i][1]).add(arr[i][0]);
        }

        for (int i=1; i <= n; i++){
            check = new int[n + 1];
            bfs(list1, i);
            bfs(list2, i);
            int ret = n;
            for (int j=1; j <= n; j++){
                if (check[j] == 1) ret--;
            }
            if (ret == 0) answer++;
        }

        return answer;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());


        br.close();
        bw.close();
    }
}
