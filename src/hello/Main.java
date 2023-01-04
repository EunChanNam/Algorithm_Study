package hello;
import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int[] arr;
    static long answer = 0;


    static int comb(int k) {
        return k * (k - 1) / 2;
    }
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
        Arrays.sort(arr);

        for (int i = 0; i < n - 2; i++) {
            if (arr[i] > 0) break;
            int lt = i + 1;
            int rt = n - 1;
            while (lt < rt) {
                int sum = arr[i] + arr[lt] + arr[rt];
                if (sum == 0) {
                    if (arr[lt] == arr[rt]) {
                        answer += comb(rt - lt + 1);
                        break;
                    }
                    int s = 1;
                    int e = 1;
                    while (arr[lt] == arr[lt + 1] && lt < rt - 1) {
                        lt++;
                        s++;
                    }
                    while (arr[rt] == arr[rt - 1] && rt > lt + 1) {
                        rt--;
                        e++;
                    }
                    lt++; rt--;
                    answer += (long)s * e;
                }
                else if (sum < 0) {
                    lt++;
                } else rt--;
            }
        }

        bw.write(String.valueOf(answer));

        br.close();
        bw.close();
    }
}
