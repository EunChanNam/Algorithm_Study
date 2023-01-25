package hello;
import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int m;
    static List<List<Integer>> list1 = new ArrayList<>();
    static List<List<Integer>> list2 = new ArrayList<>();
    static int[] check;

    static void bfs(int start, List<List<Integer>> list) {
        Queue<Integer> que = new LinkedList<>();
        que.offer(start);
        int[] visit = new int[n + 1];
        visit[start] = 1;
        int cnt = 0;
        while (!que.isEmpty()) {
            int now = que.poll();
            check[now] = 1;
            cnt++;
            for (int next : list.get(now)) {
                if (visit[next] == 1) continue;
                visit[next] = 1;
                que.offer(next);
            }
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(br.readLine());
        for (int i = 0; i <= n; i++) {
            list1.add(new ArrayList<>());
            list2.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            list1.get(a).add(b);
            list2.get(b).add(a);
        }

        for (int i = 1; i <= n; i++) {
            check = new int[n + 1];
            bfs(i, list1);
            bfs(i, list2);
            int answer = n;
            for (int j = 1; j <= n; j++) {
                if (check[j] == 1) answer--;
            }
            bw.write(String.valueOf(answer));
            bw.newLine();
        }

        br.close();
        bw.close();
    }
}
