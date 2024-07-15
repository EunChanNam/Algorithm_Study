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
        st = new StringTokenizer(br.readLine());

        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Map<Integer, Integer> map = new HashMap<>();
        int answer = 0;

        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                if (i == j) {
                    continue;
                }
                int sum = arr[i] + arr[j];
                map.put(sum, map.getOrDefault(sum, 0) + 1);
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) {
                    continue;
                }
                int sum = arr[i] + arr[j];
                map.put(sum, map.getOrDefault(sum, 0) - 1);
            }
            if (map.containsKey(arr[i]) && map.get(arr[i]) > 0) {
                answer++;
            }
            for (int j = 0; j < n; j++) {
                if (i == j) {
                    continue;
                }
                int sum = arr[i] + arr[j];
                map.put(sum, map.getOrDefault(sum, 0) + 1);
            }
        }

        bw.write(String.valueOf(answer));

        br.close();
        bw.close();
    }
}
