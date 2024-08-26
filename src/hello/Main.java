import java.util.*;
class Solution {

    private static class Node {
        String word;
        int level;
        public Node(String word, int level) {
            this.word = word;
            this.level = level;
        }
    }

    public int solution(String begin, String target, String[] wordArr) {
        int answer = 0;

        Map<String, List<String>> map = new HashMap<>();
        int n = begin.length();

        List<String> words = new ArrayList<>();
        for (String word : wordArr) {
            words.add(word);
        }
        words.add(begin);

        for (String word : words) {
            if (!map.containsKey(word)) map.put(word, new ArrayList<>());
            for (String t: words) {
                if (word.equals(t) || t.equals(begin)) continue;

                int sameCnt = 0;
                for (int i=0; i < n; i++) {
                    char a = word.charAt(i);
                    char b = t.charAt(i);
                    if (a == b) {
                        sameCnt++;
                    }
                }
                if (sameCnt == n - 1) {
                    map.get(word).add(t);
                }
            }
        }

        Set<String> set = new HashSet<>();
        set.add(begin);

        Queue<Node> que = new ArrayDeque<>();
        que.offer(new Node(begin, 0));

        while (!que.isEmpty()) {
            Node now = que.poll();
            if (now.word.equals(target)){
                return now.level;
            }

            List<String> nextList = map.get(now.word);
            for (String next : nextList) {
                if (set.contains(next)) continue;
                set.add(next);
                que.offer(new Node(next, now.level + 1));
            }
        }

        return answer;
    }
}
