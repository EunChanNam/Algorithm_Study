class Solution {
    public int solution(int m, int n, int[][] puddles) {
        int answer = 0;

        int[][] map = new int[n][m];

        for (int[] puddle :puddles) {
            int y = puddle[1] - 1;
            int x = puddle[0] - 1;
            map[y][x] = -1;
        }

        map[0][0] = 1;

        for (int y=0; y < n; y++) {
            for (int x=0; x < m; x++) {
                if (map[y][x] == -1) continue;
                int up = 0;
                int down = 0;
                if (y > 0 && map[y - 1][x] != -1) {
                    up = map[y - 1][x];
                }
                if (x > 0 && map[y][x - 1] != -1) {
                    down = map[y][x - 1];
                }

                map[y][x] += (up + down) % 1000000007;
            }
        }

        answer = map[n - 1][m - 1];

        return answer;
    }
}
