package hello;
import java.io.*;
import java.util.*;

public class Main {
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int n;
    static int m;
    static int[] arr;
    static List<Node> list = new ArrayList<>();
    static class Node{
        int a; int b; int cost;
        public Node(int a, int b, int cost) {
            this.a = a;
            this.b = b;
            this.cost = cost;
        }
    }

    static int find(int a) {
        if (arr[a] == a) return arr[a];
        return arr[a] = find(arr[a]);
    }

    static boolean union(int a, int b) {
        int fa = find(a);
        int fb = find(b);
        if (fa != fb) {
            arr[fa] = fb;
            return true;
        }
        else return false;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        arr = new int[n + 1];
        for (int i=1; i <= n; i++) arr[i] = i;
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            list.add(new Node(a, b, c));
        }
        list.sort((q, w) -> Integer.compare(q.cost, w.cost));

        int sum = 0;
        for (Node p : list){
            if (union(p.a, p.b)){
                sum += p.cost;
            }
        }

        bw.write(String.valueOf(sum));

        br.close();
        bw.close();
    }
}
}
