import java.util.*;
class Solution {

    private class Word {
        String value;
        boolean visit;
        int level;
        public Word(String value, int level) {
            this.value = value;
            this.visit = false;
            this.level = level;
        }
    }

    public int solution(String begin, String target, String[] wordArray) {
        int answer = 0;

        List<String> words = new ArrayList<>();
        for (String word : wordArray) {
            words.add(word);
        }
        words.add(begin);

        Map<String, List<Word>> map = new HashMap<>();

        for (int i=0; i < words.size(); i++) {
            for (int j=0; j < words.size() - 1; j++) {
                if (i == j) continue;
                String now = words.get(i);
                String other = words.get(j);

                int sameCnt = 0;
                for (int k=0; k < now.length(); k++) {
                    char nowCh = now.charAt(k);
                    char otherCh = other.charAt(k);
                    if (nowCh == otherCh) sameCnt++;
                }
                if (sameCnt == now.length() - 1) {
                    if (!map.containsKey(now)) {
                        map.put(now, new ArrayList<>());
                    }
                    map.get(now).add(new Word(other, 0));
                }
            }
        }

        Word start = new Word(begin, 0);
        Queue<Word> que = new ArrayDeque<>();
        que.offer(start);

        while (!que.isEmpty()) {
            Word now = que.poll();
            if (now.value.equals(target)) {
                answer = now.level;
                break;
            }

            if (!map.containsKey(now.value)) continue;
            List<Word> nextList = map.get(now.value);
            for (Word next : nextList) {
                if (next.visit) continue;
                next.visit = true;
                que.offer(new Word(next.value, now.level + 1));
            }
        }

        return answer;
    }
}
