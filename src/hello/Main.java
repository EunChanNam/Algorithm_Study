import java.util.*;
class Solution {
    public int[] solution(int[] numbers) {

        Stack<Integer> stack = new Stack<>();
        int n = numbers.length;

        Stack<Integer> result = new Stack<>();
        for (int i = n - 1; i >= 0; i--) {
            int now = numbers[i];
            while (!stack.isEmpty() && stack.peek() <= now) {
                stack.pop();
            }
            if (!stack.isEmpty() && stack.peek() > now) {
                result.push(stack.peek());
            } else {
                result.push(-1);
            }

            stack.push(now);
        }

        int[] answer = new int[n];
        for (int i=0; i < n; i++) {
            answer[i] = result.pop();
        }

        return answer;
    }
}
