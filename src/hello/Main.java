import java.util.*;
class Solution {

    private class Node {
        int y;
        int x;
        int level;
        public Node(int y, int x, int level) {
            this.y = y;
            this.x = x;
            this.level = level;
        }
    }

    private int[][] dir = {{0, -1}, {0, 1}, {1, 0}, {-1, 0}};

    public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
        int answer = 0;

        int[][] map = new int[110][110];
        int[][] lines = new int[110][110];

        for (int[] point : rectangle) {
            int x1 = point[0] * 2;
            int y1 = point[1] * 2;
            int x2 = point[2] * 2;
            int y2 = point[3] * 2;

            for (int y=y1 + 1; y < y2; y++) {
                for (int x=x1 + 1; x < x2; x++) {
                    map[y][x]++;
                }
            }
            for (int y = y1; y <= y2; y++) {
                lines[y][x1] = 1;
                lines[y][x2] = 1;
            }
            for (int x = x1; x <= x2; x++) {
                lines[y1][x] = 1;
                lines[y2][x] = 1;
            }
        }

        Node start = new Node(characterY * 2, characterX * 2, 0);
        boolean[][] visit = new boolean[110][110];
        visit[start.y][start.x] = true;

        Queue<Node> que = new ArrayDeque<>();
        que.offer(start);

        while(!que.isEmpty()) {
            Node now = que.poll();
            if (now.y == itemY * 2 && now.x == itemX * 2) {
                answer = now.level / 2;
                break;
            }

            for (int i=0; i < 4; i++) {
                int ny = now.y + dir[i][0];
                int nx = now.x + dir[i][1];

                if (visit[ny][nx]) continue;
                if (lines[ny][nx] != 1) continue;
                if (map[ny][nx] >= 1) continue;
                visit[ny][nx] = true;
                que.offer(new Node(ny, nx, now.level + 1));
            }
        }


        return answer;
    }
}
