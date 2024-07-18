package hello;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {

    private static class Road {

        int start;
        int end;
        int cost;

        public Road(int start, int end, int cost) {
            this.start = start;
            this.end = end;
            this.cost = cost;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());

        Map<Integer, List<Road>> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            if (!map.containsKey(start)) {
                map.put(start, new ArrayList<>());
            }

            map.get(start).add(new Road(start, end, cost));
        }

        int[] dp = new int[10001];
        for (int i = 1; i < 10001; i++) {
            dp[i] = Integer.MAX_VALUE;
        }
        for (int i = 0; i <= d; i++) {
            if (i != 0) {
                dp[i] = Math.min(dp[i], dp[i - 1] + 1);
            }
            if (map.containsKey(i)) {
                List<Road> roads = map.get(i);
                for (Road road : roads) {
                    dp[road.end] = Math.min(dp[road.end], dp[i] + road.cost);
                }
            }
        }

        bw.write(String.valueOf(dp[d]));

        br.close();
        bw.close();
    }
}
