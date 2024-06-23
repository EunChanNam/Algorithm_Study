import java.util.*;
class Solution {
    public long continuousSubarrays(int[] nums) {
        long answer = 0L;
        answer += nums.length;

        Deque<Integer> deque = new ArrayDeque<>();
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;

        for (int num : nums) {
            Deque<Integer> temp = new ArrayDeque<>();
            if (max != Integer.MIN_VALUE && (Math.abs(max - num) > 2 || Math.abs(min - num) > 2)) {
                max = Integer.MIN_VALUE;
                min = Integer.MAX_VALUE;
                while (!deque.isEmpty() && Math.abs(num - deque.getLast()) <= 2) {
                    int top = deque.removeLast();
                    max = Math.max(max, top);
                    min = Math.min(min, top);
                    temp.addFirst(top);
                }

                deque = temp;
            }

            deque.addLast(num);
            max = Math.max(max, num);
            min = Math.min(min, num);

            answer += deque.size() - 1;
        }

        return answer;
    }
}
