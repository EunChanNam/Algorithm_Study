package hello;
import java.io.*;
import java.util.*;

public class Main {
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int n;
    static int[] v = new int[73];
    static Stack<Integer> stack = new Stack<>();
    static int max = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            v[a]++;
            v[b]--;
        }

        for (int i = 0; i < 73; i++) {
            if (v[i] < 0) {
                for (int x = 0; x < Math.abs(v[i]); x++) {
                    stack.pop();
                }
            } else {
                for (int x = 0; x < v[i]; x++) {
                    stack.push(1);
                }
            }
            max = Math.max(max, stack.size());
        }

        System.out.println(max);
    }

}