package hello;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int s = Integer.parseInt(st.nextToken());

        int[] arr = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int left = 0;
        int right = 0;
        int sum = arr[0];

        int answer = Integer.MAX_VALUE;

        while (right < n && left < n) {
            if (sum < s) {
                right++;
                if (right < n) {
                    sum += arr[right];
                }
            } else {
                answer = Math.min(answer, right - left + 1);
                sum -= arr[left];
                left++;
                if (left > right && right + 1 < n) {
                    right++;
                    sum += arr[right];
                }
            }
        }

        if (answer == Integer.MAX_VALUE) {
            answer = 0;
        }

        bw.write(String.valueOf(answer));

        br.close();
        bw.close();
    }
}
