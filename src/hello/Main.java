import java.util.*;
class Solution {

    private static class Node {
        int price;
        int idx;
        public Node(int price, int idx) {
            this.price = price;
            this.idx = idx;
        }
    }

    public int[] solution(int[] prices) {
        int n = prices.length;
        int[] answer = new int[n];

        PriorityQueue<Node> que = new PriorityQueue<>((a, b) -> Integer.compare(b.price, a.price));

        for (int i=0; i < n; i++) {
            int price = prices[i];

            while (!que.isEmpty()) {
                Node now = que.peek();

                if (price < now.price) {
                    answer[now.idx] = i - now.idx;
                    que.poll();

                    if (!que.isEmpty()) {
                        Node next = que.peek();
                        if (price < next.price) {
                            continue;
                        }
                    }
                }

                break;
            }

            que.offer(new Node(price, i));
        }

        // que.forEach(node -> System.out.println(node.price));

        que.forEach(node -> {
            answer[node.idx] = n - node.idx - 1;
        });

        return answer;
    }
}
