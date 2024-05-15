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

        int n = Integer.parseInt(br.readLine());
        long[] arr = new long[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Long.parseLong(st.nextToken());
        }

        int start = 0;
        int end = n - 1;

        long answer1 = 0;
        long answer2 = 0;
        long minSum = Integer.MAX_VALUE;

        while (end > start) {
            long startValue = arr[start];
            long endValue = arr[end];
            long sum = endValue + startValue;

            if (Math.abs(sum) <= minSum) {
                answer1 = startValue;
                answer2 = endValue;
                minSum = Math.abs(sum);
            }

            if (sum > 0) {
                end--;
            } else {
                start++;
            }
        }

        bw.write(answer1 + " " + answer2);

        br.close();
        bw.close();
    }
}
