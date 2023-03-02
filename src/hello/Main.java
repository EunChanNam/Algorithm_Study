package hello;

import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int m;
    static int[] arr;
    static List<List<Integer>> list = new ArrayList<>();

    static int find(int a) {
        if (arr[a] == a) return a;
        else return arr[a] = find(arr[a]);
    }

    static void union(int a, int b) {
        int fa = find(a);
        int fb = find(b);
        if (fa != fb) arr[fa] = fb;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(br.readLine());
        arr = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            list.add(new ArrayList<>());
        }

        for (int i = 0; i <= n; i++) {
            arr[i] = i;
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            String s = st.nextToken();
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            if (s.equals("F")) {
                union(a, b);
            } else {
                list.get(a).add(b);
                list.get(b).add(a);
            }
        }

        for (int i = 0; i < list.size(); i++) {
            List<Integer> temp = list.get(i);
            for (int k : temp) {
                List<Integer> temp2 = list.get(k);
                for (int t : temp2) {
                    union(i, t);
                }
            }
        }

        Set<Integer> set = new HashSet<>();
        for (int i = 1; i <= n; i++) {
            set.add(find(i));
        }

        bw.write(String.valueOf(set.size()));

        br.close();
        bw.close();
    }
}