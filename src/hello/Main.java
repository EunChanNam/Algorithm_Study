import java.util.*;
class Solution {
    private static class Node {
        int profit;
        int cap;
        public Node(int profit, int cap) {
            this.profit = profit;
            this.cap = cap;
        }
    }
    public int findMaximizedCapital(int k, int w, int[] profits, int[] capital) {
        PriorityQueue<Node> pq = new PriorityQueue<>(
            (a, b) -> {
                if (a.profit == b.profit) return Integer.compare(a.cap, b.cap);
                return Integer.compare(b.profit, a.profit);
            }
        );

        int n = profits.length;
        List<Node> list = new ArrayList<>();
        for (int i=0; i < n; i++) {
            int profit = profits[i];
            int cap = capital[i];
            list.add(new Node(profit, cap));
        }

        list.sort((a, b) -> Integer.compare(a.cap, b.cap));

        int idx = 0;
        while (idx < n && w >= list.get(idx).cap) {
            pq.add(list.get(idx));
            idx++;
        }

        while (k > 0 && !pq.isEmpty()) {
            Node now = pq.poll();
            w += now.profit;
            k--;

            while (idx < n && w >= list.get(idx).cap) {
                pq.add(list.get(idx));
                idx++;
            }
        }

        return w;
    }
}
