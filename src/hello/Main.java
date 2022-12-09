package hello;
import java.io.*;
import java.util.*;

public class Main {
    public int solution(int[] a) {
        int answer = 0;
        Map<Integer, Integer> map = new HashMap<>();
        Map<Integer, Integer> map2 = new HashMap<>();
        for (int i=0; i < a.length; i++){
            map.put(a[i], map.getOrDefault(a[i], 0) + 1);
        }

        for (int i=0; i < a.length; i++){
            map2.put(a[i], map2.getOrDefault(a[i], 0) + 1);
            if (map.get(a[i]) == 1){
                map.remove(a[i]);
            } else {
                map.put(a[i], map.get(a[i]) - 1);
            }

            if (map.size() == map2.size()) answer++;
        }

        return answer;
    }

    public static void main(String[] args) {

    }
}
