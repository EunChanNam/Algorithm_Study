package hello;
import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int m;
    static PriorityQueue<Long> que = new PriorityQueue<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            que.offer(Long.parseLong(st.nextToken()));
        }

        int cnt = 0;
        while (cnt < m) {
            long a = que.poll();
            long b = que.poll();
            long sum = a + b;
            que.offer(sum);
            que.offer(sum);
            cnt++;
        }

        long ret = que.stream().reduce(Long::sum).get();
        bw.write(String.valueOf(ret));

        br.close();
        bw.close();
    }
}
