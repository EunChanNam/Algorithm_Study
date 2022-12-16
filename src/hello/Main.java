package hello;
import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int[] arr;



    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());
        arr = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int[] answer = new int[n];
        for (int i = 0; i < n; i++) {
            int cnt = 0;
            for (int x = 0; x < n; x++) {
                if (cnt == arr[i]) {
                    int t = x;
                    while (answer[t] != 0) t++;
                    answer[t] = i + 1;
                    break;
                }
                if (answer[x] == 0) cnt++;
            }
        }

        for (int i = 0; i < n; i++) {
            if (i == n - 1) bw.write(String.valueOf(answer[i]));
            else bw.write(answer[i] + " ");
        }

        br.close();
        bw.close();
    }
}
