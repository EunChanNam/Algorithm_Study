import java.util.*;
class Solution {
    public int monotoneIncreasingDigits(int n) {
        String s = String.valueOf(n);
        Stack<Character> stack = new Stack<>();

        boolean isDecre = false;
        for (char ch : s.toCharArray()) {
            if (stack.isEmpty()) {
                stack.push(ch);
                continue;
            }

            if (isDecre) {
                stack.push('9');
                continue;
            }

            int decreaseCnt = 0;
            Stack<Character> temp = new Stack<>();
            while (!stack.isEmpty() && stack.peek() > ch) {
                char top = stack.pop();
                ch = --top;
                temp.push(ch);
                decreaseCnt++;
                isDecre = true;
            }
            if (!temp.isEmpty()) {
                stack.push(temp.pop());
            }
            for (int i=0; i < decreaseCnt; i++) {
                stack.push('9');
            }

            if (decreaseCnt == 0) {
                stack.push(ch);
            }
        }

        StringBuilder sb = new StringBuilder();
        stack.forEach(ch -> sb.append(ch));
        return Integer.parseInt(sb.toString());
    }
}
