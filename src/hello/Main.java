package hello;

import java.io.*;
import java.util.*;
import java.util.stream.IntStream;

public class Main {

    static int n;
    static int k;
    static int combo;
    static int coupon;

    static List<Integer> dishes = new ArrayList<>();

    static void initSlide(Map<Integer, Integer> slide) {
        IntStream.range(0, combo)
                .forEach(i -> {
                    int target = dishes.get(i);
                    slide.put(target, slide.getOrDefault(target, 0) + 1);
                });
    }

    static void goNextSlide(int start, int end, Map<Integer, Integer> slide) {
        int startDish = dishes.get(start);
        int endDish = dishes.get(end);

        if (slide.get(startDish) == 1) {
            slide.remove(startDish);
        } else slide.put(startDish, slide.get(startDish) - 1);

        slide.put(endDish, slide.getOrDefault(endDish, 0) + 1);
    }

    static int findMaxDish() {

        int start = 0;
        int end =  combo - 1;

        Map<Integer, Integer> slide = new HashMap<>();

        initSlide(slide);
        int result = slide.size();

        do {
            if (end == n - 1) end = -1;
            goNextSlide(start, end + 1, slide);

            int nowSize = slide.size();
            if (!slide.containsKey(coupon)) {
                nowSize++;
            }

            result = Math.max(result, nowSize);

            start++;
            end++;
            if (start == n) start = 0;
        } while (end != combo - 1);

        return result;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        combo = Integer.parseInt(st.nextToken());
        coupon = Integer.parseInt(st.nextToken());

        for (int i = 0; i < n; i++) {
            int input = Integer.parseInt(br.readLine());
            dishes.add(input);
        }

        int answer = findMaxDish();

        bw.write(String.valueOf(answer));

        br.close();
        bw.close();
    }
}