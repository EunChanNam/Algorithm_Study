import java.util.*;
class Solution {
    public int minimumLines(int[][] params) {
        List<List<Integer>> stockPrices = new ArrayList<>();
        for (int[] param : params) {
            List<Integer> temp = new ArrayList<>();
            temp.add(param[0]);
            temp.add(param[1]);

            stockPrices.add(temp);
        }

        if (stockPrices.size() == 1) return 0;

        stockPrices.sort((a, b) -> {
            if (a.get(0) == b.get(0)) {
                return Integer.compare(a.get(1), b.get(1));
            }
            return Integer.compare(a.get(0), b.get(0));
        });

        int cnt = 1;
        for (int i=1; i < stockPrices.size() - 1; i++) {
            List<Integer> pre = stockPrices.get(i - 1);
            List<Integer> now = stockPrices.get(i);
            List<Integer> next = stockPrices.get(i + 1);

            int gap1 = (now.get(0) - pre.get(0)) * (next.get(1) - now.get(1));
            int gap2 = (now.get(1) - pre.get(1)) * (next.get(0) - now.get(0));

            if (gap1 != gap2) cnt++;
        }

        return cnt;
    }
}
