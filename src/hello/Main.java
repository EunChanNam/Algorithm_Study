class Solution {
    public int solution(String name) {
        int answer = 0;

        int move = name.length() - 1;
        for (int i=0; i < name.length(); i++) {
            char ch = name.charAt(i);
            answer += getCost(ch);

            int idx = i + 1;
            int cnt = 0;
            while (idx < name.length() && name.charAt(idx) == 'A') {
                cnt++;
                idx++;
            }

            move = Math.min(move, i - cnt + name.length() - 1);
            move = Math.min(move, (name.length() - idx) - cnt + name.length() - 1);
        }

        return answer + move;
    }

    private int getCost(char target) {
        int a = target - 'A';
        int b = 'Z' - target + 1;
        return Math.min(a, b);
    }
}
