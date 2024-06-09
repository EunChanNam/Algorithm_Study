class Solution {
    public int solution(int[] money) {
        int answer = 0;

        answer = Math.max(getByDp(true, money), getByDp(false, money));

        return answer;
    }

    private int getByDp(boolean isFirstSelect, int[] money) {
        int n = money.length;
        int[] dp = new int[n];
        int max = 0;
        int i = 1;
        if (isFirstSelect) {
            dp[0] = money[0];
            n--;
            i++;
        }

        for (; i < n; i++) {
            int preMax;
            if (i - 2 < 0) {
                preMax = 0;
            } else if (i - 3 < 0) {
                preMax = dp[i - 2];
            } else {
                preMax = Math.max(dp[i - 2], dp[i - 3]);
            }

            dp[i] = money[i] + preMax;

            max = Math.max(dp[i], max);
        }

        return max;
    }
}
