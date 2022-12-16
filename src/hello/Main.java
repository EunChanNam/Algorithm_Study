package hello;
import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static Node[] arr;
    static PriorityQueue<Integer> que = new PriorityQueue<>(Comparator.reverseOrder());
    static class Node{
        int val; int d;
        public Node(int val, int d){
            this.val = val;
            this.d = d;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());
        if (n == 0) {
            bw.write("0");
            br.close();
            bw.close();
            return;
        }
        arr = new Node[n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            arr[i] = new Node(a, b);
        }
        Arrays.sort(arr, (q, w) -> Integer.compare(w.d, q.d));

        int answer = 0;
        int start = arr[0].d;
        int x = 0;
        for (int y = start; y >= 1; y--) {
            for (; x < n; x++) {
                if (arr[x].d < y) break;
                que.offer(arr[x].val);
            }
            if (!que.isEmpty()) answer += que.poll();
        }

        bw.write(String.valueOf(answer));

        br.close();
        bw.close();
    }
}
