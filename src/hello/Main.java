package hello;

import java.io.*;
import java.util.*;

public class Main {

    static class Node {
        int start; int end;
        public Node(int start, int end){
            this.start = start;
            this.end = end;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());

        Node[] arr = new Node[n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            arr[i] = new Node(a, b);
        }

        Arrays.sort(arr, (a, b) -> {
            if (a.start == b.start) {
                return Integer.compare(a.end, b.end);
            }
            return Integer.compare(a.start, b.start);
        });

        int start = arr[0].start;
        int end = arr[0].end;
        int len = 0;
        for (int i = 1; i < n; i++) {
            Node now = arr[i];
            if (now.start > end) {
                len += end - start;
                start = now.start;
                end = now.end;
                continue;
            }
            if (now.end > end) {
                end = now.end;
            }
        }

        int answer = len + end - start;

        bw.write(String.valueOf(answer));

        br.close();
        bw.close();
    }
}