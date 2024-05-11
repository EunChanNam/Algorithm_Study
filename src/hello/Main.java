package hello;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int start = 0;
        int end = 0;

        Map<Integer, Integer> numberMap = new HashMap<>();
        for (int value : arr) {
            numberMap.put(value, 0);
        }
        numberMap.put(arr[start], 1);

        int answer = 0;
        while (end != n - 1) {
            if (numberMap.get(arr[end + 1]) < m) {
                end++;
                numberMap.put(arr[end], numberMap.get(arr[end]) + 1);
            }
            else {
                numberMap.put(arr[start], numberMap.get(arr[start]) - 1);
                start++;
            }
            answer = Math.max(answer, end - start + 1);
        }

        bw.write(String.valueOf(answer));

        br.close();
        bw.close();
    }
}
