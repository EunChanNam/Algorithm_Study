package hello;
import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static PriorityQueue<Integer> que = new PriorityQueue<>();


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            int a = Integer.parseInt(br.readLine());
            arr[i] = a;
        }
        for (int a : arr) {
            que.offer(a);
        }

        int answer = 0;
        while (que.size() != 1) {
            int a = que.poll();
            int b = que.poll();
            int sum = a + b;
            answer += sum;
            que.offer(sum);
        }

        bw.write(String.valueOf(answer));

        br.close();
        bw.close();
    }
}
