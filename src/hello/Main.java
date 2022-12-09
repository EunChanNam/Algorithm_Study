package hello;
import java.io.*;
import java.util.*;

public class Main {
    static int[] v;
    static int[] visit;
    static int find(int a, int cnt){
        visit[a] = 1;
        if (v[a] == a + 1) return cnt;
        else if (visit[v[a] - 1] == 1) return cnt;
        else return find(v[a] - 1, cnt + 1);
    }
    public int solution(int[] cards) {
        int answer = 0;
        v = cards;
        visit = new int[v.length];

        List<Integer> list = new ArrayList<>();
        for (int i=0; i < v.length; i++){
            if (visit[i] != 1){
                int k = find(i, 1);
                list.add(k);
            }
        }

        if (list.size() == 1) return answer;
        answer = 1;
        list.sort((a, b) -> b.compareTo(a));
        answer = list.get(0) * list.get(1);

        return answer;
    }

    public static void main(String[] args) {

    }
}
