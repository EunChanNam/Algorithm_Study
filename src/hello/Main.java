class Solution {

    public int minimumCoins(int[] prices) {
        int n = prices.length;
        int dp1[] = new int[n];
        int dp2[] = new int[n];

        dp1[0] = prices[0];
        dp2[0] = prices[0];

        for (int i=1; i < n; i++) {
            dp1[i] = Math.min(dp1[i - 1], dp2[i - 1]) + prices[i];

            int min = Integer.MAX_VALUE;
            for (int j = i / 2; j <= i; j++) {
                min = Math.min(min, dp1[j]);
            }
            dp2[i] = min;
        }

        return Math.min(dp1[n - 1], dp2[n - 1]);
    }
}
