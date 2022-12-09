package hello;
import java.io.*;
import java.util.*;

public class Main {
    public int solution(int[] a, int[] b) {
        int answer = 0;

        Arrays.sort(a);
        Arrays.sort(b);
        int max = 0;

        for (int i=a[0]; i >= 1; i--){
            boolean flag2 = true;
            for (int x=0; x < a.length; x++){
                if (a[x] % i != 0 || b[x] % i == 0){
                    flag2 = false;
                    break;
                }
            }
            if (flag2) {
                max = i;
                break;
            }
        }
        for (int i=b[0]; i >= 1; i--){
            boolean flag = true;
            for (int x=0; x < b.length; x++){
                if (b[x] % i != 0 || a[x] % i == 0){
                    flag = false;
                    break;
                }
            }
            if (flag) {
                max = Math.max(max, i);
                break;
            }
        }

        answer = max;

        return answer;
    }

    public static void main(String[] args) {

    }
}
