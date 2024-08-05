import java.util.*;
class Solution {
    public int solution(String numbers) {
        int answer = 0;
        n = numbers.length();

        for (char ch : numbers.toCharArray()) {
            list.add((int) ch - '0');
        }

        visit = new boolean[n];
        dfs(0);
        answer = cnt;

        return answer;
    }

    private static int n;
    private static Set<Integer> set = new HashSet<>();
    private static List<Integer> list = new ArrayList<>();
    private static int cnt = 0;
    private static boolean[] visit;
    private static StringBuilder sb = new StringBuilder();

    private void dfs(int level) {
        if (level == n + 1) return;

        String str = sb.toString();
        if (!str.isEmpty()) {
            int num = Integer.parseInt(str);

            if (isPrime(num) && !set.contains(num)) {
                set.add(num);
                cnt++;
            }
        }

        for (int i=0; i < n; i++) {
            if (visit[i]) continue;
            int next = list.get(i);

            visit[i] = true;
            sb.append(next);
            dfs(level + 1);
            sb.deleteCharAt(sb.length() - 1);
            visit[i] = false;
        }
    }

    public static boolean isPrime(int number) {
        if (number <= 1) {
            return false;
        }
        if (number == 2) {
            return true;
        }
        if (number % 2 == 0) {
            return false;
        }
        for (int i = 3; i <= Math.sqrt(number); i += 2) {
            if (number % i == 0) {
                return false;
            }
        }

        return true;
    }
}
