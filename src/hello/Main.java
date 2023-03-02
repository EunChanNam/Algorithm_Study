package hello;

import java.io.*;
import java.util.*;

public class Main {
    static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            String s = br.readLine();
            sb.append(s);
        }

        String str = sb.toString();
        String answer = "";
        int lt = 0;
        int rt = n - 1;

        while (rt >= lt) {
            char left = str.charAt(lt);
            char right = str.charAt(rt);
            if (rt == lt) {
                answer += left;
                break;
            }
            else if (left < right) {
                answer += left;
                lt++;
            } else if (right < left) {
                answer += right;
                rt--;
            } else {
                if (rt - 1 == lt) {
                    answer += left;
                    answer += right;
                    break;
                }
                int lt2 = lt + 1;
                int rt2 = rt - 1;
                while (true) {
                    if (lt2 >= rt2) {
                        answer += left;
                        answer += right;
                        lt++; rt--;
                        break;
                    }
                    char left2 = str.charAt(lt2);
                    char right2 = str.charAt(rt2);
                    if (left2 < right2) {
                        answer += left;
                        lt++;
                        break;
                    }
                    if (right2 < left2) {
                        answer += right;
                        rt--;
                        break;
                    }
                    else {
                        lt2++; rt2--;
                    }
                }
            }
        }

        for (int i = 0; i < n; i++) {
            if (i % 80 == 0 && i != 0) bw.newLine();
            bw.write(String.valueOf(answer.charAt(i)));
        }

        br.close();
        bw.close();
    }
}