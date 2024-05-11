package hello;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {

    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String target = br.readLine();
        int n = target.length();

        int aCnt = 0;
        for (char ch : target.toCharArray()) {
            if (ch == 'a') {
                aCnt++;
            }
        }

        if (aCnt == 0) {
            bw.write("0");
            br.close();
            bw.close();
            return;
        }

        int start = 0;
        int end = aCnt - 1;
        int answer;

        int bCount = 0;
        for (int i = start; i <= end; i++) {
            char ch = target.charAt(i);
            if (ch == 'b') {
                bCount++;
            }
        }
        answer = bCount;

        int lastIndex = end - 1;
        if (start == end) {
            lastIndex = n - 1;
        }

        while (end != lastIndex) {
            char preStart = target.charAt(start);
            end++;
            start++;
            if (end == n) {
                end = 0;
            }
            if (start == n) {
                start = 0;
            }
            char nowEnd = target.charAt(end);

            if (preStart == 'b') bCount--;
            if (nowEnd == 'b') bCount++;

            answer = Math.min(answer, bCount);
        }

        bw.write(String.valueOf(answer));

        br.close();
        bw.close();
    }
}
