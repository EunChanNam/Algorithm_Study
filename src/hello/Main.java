package hello;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    private static class Node {
        int level;
        List<Integer> children;
        int stack = 0;
        int childCnt = 0;
        int depth = 0;
        Node parent;

        public Node(int level, int... child) {
            this.level = level;
            children = new ArrayList<>();
            for (int c : child) {
                children.add(c);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());

        List<Node> nodes = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int num = Integer.parseInt(st.nextToken());
            if (num == -1) {
                nodes.add(new Node(-1));
                continue;
            }
            Node node = new Node(0);
            node.level = nodes.get(num).level + 1;
            nodes.add(node);
            nodes.get(num).children.add(i);
            node.parent = nodes.get(num);

            Node parent = nodes.get(num);

            while (parent != null) {
                parent.childCnt++;
                parent.depth = node.level;
                parent = parent.parent;
            }
        }

        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a.level));

        nodes.forEach(pq::offer);

        int max = 0;
        while (!pq.isEmpty()) {
            Node node = pq.poll();
            int stack = 1;
            node.children.sort((a, b) -> {
                Node aNode = nodes.get(a);
                Node bNode = nodes.get(b);
                if (aNode.depth == bNode.depth) {
                    return Integer.compare(bNode.childCnt, aNode.childCnt);
                }
                return Integer.compare(bNode.depth, aNode.depth);
            });
            for (int child : node.children) {
                Node next = nodes.get(child);
                next.stack = node.stack + stack;
                stack++;

                max = Math.max(max, next.stack);
            }
        }

        bw.write(String.valueOf(max));

        br.close();
        bw.close();
    }
}
