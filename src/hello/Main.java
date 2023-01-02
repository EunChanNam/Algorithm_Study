package hello;
import java.io.*;
import java.util.*;

public class Main {
    public int solution(int n, int k, int[] e) {
        int answer = e.length;

        PriorityQueue<Integer> que = new PriorityQueue<>();
        for (int i=0; i < e.length; i++){
            que.offer(e[i]);

            if (que.size() > k){
                n -= que.poll();
            }

            if (n < 0) {
                answer = i;
                break;
            }
        }

        return answer;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;


        br.close();
        bw.close();
    }
}
