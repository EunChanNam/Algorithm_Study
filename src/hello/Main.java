package hello;
import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static Node[] arr;
    static class Node{
        int start; int end;
        public Node(int start, int end){
            this.start = start;
            this.end = end;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        arr = new Node[n];
        for (int i=0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            arr[i] = new Node(a, b);
        }

        Arrays.sort(arr, (q, w) -> {
            if (q.end == w.end) return Integer.compare(q.start, w.start);
            else return Integer.compare(q.end, w.end);
        });

        int now = arr[0].end;
        int cnt = 1;
        for (int i=1; i < n; i++){
            Node p = arr[i];
            if (p.start >= now){
                cnt++;
                now = p.end;
            }
        }

        bw.write(String.valueOf(cnt));

        br.close();
        bw.close();
    }
}
