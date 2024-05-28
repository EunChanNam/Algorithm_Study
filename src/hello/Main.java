class Solution {
    public int solution(String word) {
        int answer = 0;

        dfs(0, "", word);
        answer = cnt;

        return answer;
    }

    private static char[] arr = {'A', 'E', 'I', 'O', 'U'};

    private boolean stop = false;
    private int cnt = -1;
    private void dfs(int level, String now, String target) {
        if (stop) return;
        cnt++;
        if (now.equals(target)) {
            stop = true;
            return;
        }
        if (level >= 5) {
            return;
        }

        for (char ch : arr) {
            StringBuilder sb = new StringBuilder(now);
            sb.append(ch);
            String next = sb.toString();
            dfs(level + 1, next, target);
        }
    }
}
