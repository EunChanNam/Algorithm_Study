package hello;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        Map<String, Integer> map = new HashMap<>();
        Set<String> set = new HashSet<>();

        int n = Integer.parseInt(br.readLine());
        List<String> list = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            String str = br.readLine();
            list.add(str);
        }

        list.forEach(str -> {
            if (!set.contains(str)) {
                StringBuilder sb = new StringBuilder();
                for (char ch : str.toCharArray()) {
                    sb.append(ch);
                    String now = sb.toString();
                    map.put(now, map.getOrDefault(now, 0) + 1);
                }
                set.add(str);
            }
        });

        List<String> keys = new ArrayList<>(map.keySet())
            .stream()
            .filter(key -> map.get(key) >= 2)
            .collect(Collectors.toList());
        keys.sort((a, b) -> Integer.compare(b.length(), a.length()));
        int maxLength = keys.get(0).length();

        List<String> maxKeys = new ArrayList<>();
        for (String key : keys) {
            if (key.length() != maxLength) {
                break;
            }
            maxKeys.add(key);
        }

        int cnt = 0;
        String maxKey = "";
        for (String str : list) {
            for (String key : maxKeys) {
                if (str.startsWith(key)) {
                    maxKey = key;
                    break;
                }
            }
            if (!maxKey.isEmpty()) {
                break;
            }
        }
        for (String str : list) {
            if (cnt == 2) {
                break;
            }

            if (str.startsWith(maxKey)) {
                System.out.println(str);
                cnt++;
            }
        }

        br.close();
        bw.close();
    }
}
