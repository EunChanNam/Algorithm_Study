/**
 * // This is the interface that allows for creating nested lists.
 * // You should not implement it, or speculate about its implementation
 * public interface NestedInteger {
 *     // Constructor initializes an empty nested list.
 *     public NestedInteger();
 *
 *     // Constructor initializes a single integer.
 *     public NestedInteger(int value);
 *
 *     // @return true if this NestedInteger holds a single integer, rather than a nested list.
 *     public boolean isInteger();
 *
 *     // @return the single integer that this NestedInteger holds, if it holds a single integer
 *     // Return null if this NestedInteger holds a nested list
 *     public Integer getInteger();
 *
 *     // Set this NestedInteger to hold a single integer.
 *     public void setInteger(int value);
 *
 *     // Set this NestedInteger to hold a nested list and adds a nested integer to it.
 *     public void add(NestedInteger ni);
 *
 *     // @return the nested list that this NestedInteger holds, if it holds a nested list
 *     // Return empty list if this NestedInteger holds a single integer
 *     public List<NestedInteger> getList();
 * }
 */
import java.util.*;
class Solution {
    public NestedInteger deserialize(String s) {
        // [ 를 만나면 스택에 List 추가
        // 숫자를 만나면 스택에서 peek() 하고 거기 리스트에 숫자 추가
        // ] 를 만나면 스택에 List가 있으면 거기에 add, 없으면 종료

        Stack<NestedInteger> stack = new Stack<>();
        for (int i=0; i < s.length(); i++) {
            char ch = s.charAt(i);

            if (ch == '[') {
                stack.push(new NestedInteger());
            }
            if (ch >= '0' && ch <= '9' || ch == '-') {
                StringBuilder sb = new StringBuilder();
                sb.append(ch);

                i++;
                while (i < s.length() && (s.charAt(i) >= '0' && s.charAt(i) <= '9')) {
                    sb.append(s.charAt(i++));
                }
                i--;

                if (!stack.isEmpty()) {
                    stack.peek().add(new NestedInteger(Integer.parseInt(sb.toString())));
                } else {
                    stack.push(new NestedInteger(Integer.parseInt(sb.toString())));
                }
            }
            if (ch == ']') {
                NestedInteger top = stack.pop();
                if (top.isInteger()) top = new NestedInteger(top.getInteger());
                if (!stack.isEmpty()) {
                    stack.peek().add(top);
                } else stack.push(top);
            }
        }

        return stack.pop();
    }
}
