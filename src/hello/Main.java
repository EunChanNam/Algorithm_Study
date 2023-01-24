package hello;
import java.io.*;
import java.util.*;

public class Main {
    static int n;

    static class Node{
        int val; int k;
        public Node(int val, int k) {
            this.val = val;
            this.k = k;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        int len; int w;
        len = Integer.parseInt(st.nextToken());
        w = Integer.parseInt(st.nextToken());

        Queue<Integer> que = new LinkedList<>();
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            que.offer(Integer.parseInt(st.nextToken()));
        }

        List<Node> list = new ArrayList<>();
        int size = 0;
        int sum = 0;
        int answer = 0;
        while (!list.isEmpty() || !que.isEmpty()) {
            answer++;
            for (int i = list.size() - 1; i >= 0; i--) {
                list.set(i, new Node(list.get(i).val, list.get(i).k - 1));
                if (list.get(i).k == 0) {
                    size--;
                    sum -= list.get(i).val;
                    list.remove(i);
                }
            }
            if (!que.isEmpty()) {
                int p = que.peek();
                if (size + 1 <= len && sum + p <= w) {
                    list.add(new Node(p, len));
                    size += 1;
                    sum += p;
                    que.poll();
                }
            }
        }

        bw.write(String.valueOf(answer));

        br.close();
        bw.close();
    }
}
