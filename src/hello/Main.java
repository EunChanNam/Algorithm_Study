package hello;
import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(bf.readLine());
        int[] a = new int[n];
        StringTokenizer st = new StringTokenizer(bf.readLine());
        for (int i = 0; i < n; i++) {
            a[i] = Integer.parseInt((st.nextToken()));
        }

        Arrays.sort(a);

        int lt=0; int rt=n-1;
        int min = Integer.MAX_VALUE;
        int minX=0; int minY = 0;
        while (rt > lt) {
            int sum = a[lt] + a[rt];
            int abs = Math.abs(sum);
            if (sum == 0) {
                minX = lt; minY = rt;
                break;
            } else if (sum > 0) {
                if (abs < min) {
                    min = abs;
                    minX = lt; minY = rt;
                }
                rt--;
            } else {
                if (abs < min) {
                    min = abs;
                    minX = lt; minY = rt;
                }
                lt++;
            }
        }

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));//선언
        String one = String.valueOf(a[minX]);
        String two = String.valueOf(a[minY]);
        bw.write(one + " " + two);
        bf.close();
        bw.close();
    }
}