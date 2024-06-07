import java.util.*;
class Solution {
    public int solution(int[][] triangles) {
        int answer = 0;

        List<Integer[]> dp = new ArrayList<>();
        for (int[] triangle : triangles) {
            Integer[] arr = new Integer[triangle.length];
            dp.add(arr);
        }

        dp.get(0)[0] = triangles[0][0];

        for (int y=1; y < triangles.length; y++) {
            Integer[] preDp = dp.get(y - 1);
            Integer[] nowDp = dp.get(y);
            for (int x=0; x < nowDp.length; x++) {
                if (x == nowDp.length - 1) {
                    nowDp[x] = triangles[y][x] + preDp[x - 1];
                } else if (x == 0) {
                    nowDp[x] = triangles[y][x] + preDp[x];
                } else {
                    int max = Math.max(preDp[x], preDp[x - 1]);
                    nowDp[x] = triangles[y][x] + max;
                }
                answer = Math.max(answer, nowDp[x]);
            }
        }

        return answer;
    }
}
