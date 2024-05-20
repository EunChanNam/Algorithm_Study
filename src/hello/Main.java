import java.util.*;
import java.util.stream.*;
class Solution {

    private class Node {
        String genre;
        int play;
        int idx;
        public Node(String genre, int play, int idx) {
            this.genre = genre;
            this.play = play;
            this.idx = idx;
        }
    }

    public int[] solution(String[] genres, int[] plays) {
        int n = genres.length;
        List<Integer> answerList = new ArrayList<>();

        Map<String, List<Node>> map = new HashMap<>();

        for (int i=0; i < n; i++){
            String genre = genres[i];
            int play = plays[i];

            if (map.get(genre) == null) {
                map.put(genre, new ArrayList<>());
            }
            map.get(genre).add(new Node(genre, play, i));
        }

        List<List<Node>> list = map.values().stream().collect(Collectors.toList());
        list.sort((a, b) -> {
            int sumA = 0;
            for (Node node : a) {
                sumA += node.play;
            }
            int sumB = 0;
            for (Node node : b) {
                sumB += node.play;
            }
            return Integer.compare(sumB, sumA);
        });

        list.forEach(nodes -> {
            nodes.sort((a, b) -> {
                if (a.play == b.play) {
                    return Integer.compare(a.idx, b.idx);
                }
                return Integer.compare(b.play, a.play);
            });
        });

        for (List<Node> nodes : list) {
            int songCnt = 0;
            for (Node node : nodes) {
                if (songCnt == 2) {
                    continue;
                }
                answerList.add(node.idx);
                songCnt++;
            }
        }

        int[] answer = new int[answerList.size()];
        for (int i=0; i < answerList.size(); i++){
            answer[i] = answerList.get(i);
        }

        return answer;
    }
}
