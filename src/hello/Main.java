import java.util.*;
import java.util.stream.Collectors;
class Solution {
    public int solution(int[] cards) {
        int answer = 0;

        int n = cards.length;
        int[] uf = new int[n + 1];
        for (int i=0; i <= n; i++) {
            uf[i] = i;
        }

        for (int i=0; i < n; i++) {
            int k = i + 1;
            union(k, cards[i], uf);
        }

        Map<Integer, Integer> map = new HashMap<>();

        for (int card : cards) {
            int group = find(card, uf);
            map.put(group, map.getOrDefault(group, 0) + 1);
        }

        List<Integer> list = map.values().stream().collect(Collectors.toList());
        if (list.size() == 1) {
            return answer;
        }

        list.sort((a, b) -> Integer.compare(b, a));
        answer = list.get(0) * list.get(1);

        return answer;
    }

    private int find(int a, int[] uf) {
        if (uf[a] == a) return a;
        return uf[a] = find(uf[a], uf);
    }

    private void union(int a, int b, int[] uf) {
        int fa = find(a, uf);
        int fb = find(b, uf);

        if (fa != fb) uf[fa] = fb;
    }
}
