package hello;
import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int[] a;
    static int[] temp;
    static int[] b;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        a = new int[n];
        temp = new int[n];
        b = new int[n];
        String str = br.readLine();
        for (int i=0; i < n; i++) {
            int k = str.charAt(i) - '0';
            a[i] = k;
            temp[i] = k;
        }
        str = br.readLine();
        for (int i=0; i < n; i++) {
            int k = str.charAt(i) - '0';
            b[i] = k;
        }

        //키고 시작할 때
        change(a, 0);
        change(a, 1);
        int cnt1 = run(a, 1);
        int cnt2 = run(temp, 0);
        int answer = 0;
        if (cnt1 == -1 && cnt2 == -1) answer = -1;
        else if (cnt1 == -1) answer = cnt2;
        else if (cnt2 == -1) answer = cnt1;
        else answer = Math.min(cnt1, cnt2);

        bw.write(String.valueOf(answer));


        br.close();
        bw.close();
    }

    static int run(int[] arr, int cnt) {
        for (int i = 1; i < n; i++) {
            if (arr[i - 1] != b[i - 1]) {
                change(arr, i - 1);
                change(arr, i);
                if (i != n - 1) change(arr, i + 1);
                cnt++;
            }
        }
        if (arr[n - 1] != b[n - 1]) cnt = -1;
        return cnt;
    }

    static void change(int[] arr, int index) {
        if (arr[index] == 0) arr[index] = 1;
        else arr[index] = 0;
    }
}
