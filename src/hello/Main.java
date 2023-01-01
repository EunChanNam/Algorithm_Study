package hello;
import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for (int z = 0; z < T; z++) {
            int n = Integer.parseInt(br.readLine());
            int[] arr = new int[n];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }
            int big = arr[n - 1];
            long answer = 0;
            for (int i = n - 2; i >= 0; i--) {
                if (arr[i] >= big) big = arr[i];
                else {
                    answer += big - arr[i];
                }
            }
            bw.write(String.valueOf(answer));
            bw.newLine();
        }

        br.close();
        bw.close();
    }
}
