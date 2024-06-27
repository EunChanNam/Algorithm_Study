import java.util.*;
class Solution {

    private static class Node {
        int y;
        int x;
        int cost;

        public boolean equals(Object object) {
            Node target = (Node) object;
            return (this.y == target.y && this.x == target.x);
        }

        public int hashCode() {
            return Objects.hash(y, x);
        }

        public Node(int y, int x, int cost) {
            this.y = y;
            this.x = x;
            this.cost = cost;
        }
    }

    public int minimumCost(int[] startArr, int[] targetArr, int[][] specialRoads) {

        PriorityQueue<Node> que = new PriorityQueue<>((a, b) -> Integer.compare(a.cost, b.cost));
        Node start = new Node(startArr[1], startArr[0], 0);
        Node target = new Node(targetArr[1], targetArr[0], 0);
        que.offer(start);

        Set<Node> visit = new HashSet<>();

        while (!que.isEmpty()) {
            Node now = que.poll();
            if (now.equals(target)) {
                return now.cost;
            }

            if (visit.contains(now)) continue;
            visit.add(now);

            que.offer(new Node(target.y, target.x, now.cost + getDis(now, target)));
            for (int[] road : specialRoads) {
                int nextCost = now.cost + getDis(now, road[0], road[1]) + road[4];
                que.offer(new Node(road[3], road[2], nextCost));
            }
        }

        return -1;
    }

    public int getDis(Node a, Node b) {
        return Math.abs(b.x - a.x) + Math.abs(b.y - a.y);
    }

    public int getDis(Node a, int targetX, int targetY) {
        return Math.abs(targetX - a.x) + Math.abs(targetY - a.y);
    }
}
