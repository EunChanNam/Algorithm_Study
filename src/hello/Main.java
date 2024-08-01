import java.util.*;
class Solution {
    public String solution(int[] numbers) {
        String answer = "";
        int n = numbers.length;

        List<String> strs = new ArrayList<>();

        for (int i=0; i < n; i++) {
            strs.add(String.valueOf(numbers[i]));
        }

        strs.sort((a, b) -> {
            String order1 = a + b;
            String order2 = b + a;
            return order2.compareTo(order1);
        });

        StringBuilder sb = new StringBuilder();
        strs.forEach(str -> sb.append(str));

        answer = sb.toString();
        if (!answer.isEmpty() && answer.charAt(0) == '0') {
            return "0";
        }

        return answer;
    }
}
