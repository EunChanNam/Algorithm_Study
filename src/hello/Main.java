import java.util.*;
class Solution {
    public long putMarbles(int[] weights, int k) {
        int n = weights.length;


        long leftStart = weights[0];
        long rightEnd = weights[n - 1];

        List<Long> sumList = new ArrayList<>();
        for (int i=0; i < n - 1; i++) {
            long nowWeight = weights[i];
            long nextWeight = weights[i + 1];
            long sum = nowWeight + nextWeight;

            sumList.add(sum);
        }

        List<Long> ascList = new ArrayList<>(sumList);
        List<Long> descList = new ArrayList<>(sumList);
        ascList.sort((a, b) -> Long.compare(a, b));
        descList.sort((a, b) -> Long.compare(b, a));

        long min = 0;
        long max = 0;

        int ascIdx = 0;
        int descIdx = 0;
        for (int i=0; i < k - 1; i++) {
            min += ascList.get(ascIdx++);
            max += descList.get(descIdx++);
        }

        return max - min;
    }
}
