import java.util.*;
class Solution {
    private static class Node {
        int height;
        int idx;
        public Node(int height, int idx) {
            this.height = height;
            this.idx = idx;
        }
    }

    public int maximalRectangle(char[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;

        int max = 0;
        int[] height = new int[m];
        for (int y=0; y < n; y++) {
            for (int x=0; x < m; x++) {
                char k = matrix[y][x];
                if (k == '1') height[x]++;
                else height[x] = 0;
            }

            List<Node> list = new ArrayList<>();
            for (int x=0; x < m; x++) {
                max = Math.max(max, height[x]);
                if (height[x] == 0) list = new ArrayList<>();

                boolean flag = false;
                boolean flag2 = true;
                int maxHeight = 0;
                for (Node node : list) {
                    maxHeight = Math.max(maxHeight, node.height);
                    int witdh = x - node.idx + 1;
                    int h = Math.min(node.height, height[x]);
                    if (height[x] < node.height) {
                        node.height = height[x];
                    }

                    max = Math.max(max, witdh * h);
                }
                if (list.isEmpty() || height[x] > maxHeight) list.add(new Node(height[x], x));
            }
        }

        return max;
    }
}
