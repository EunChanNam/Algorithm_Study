package hello;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {

    /**
     * 수열에서 연속한 1개 이상의 수를 뽑았을 때 같은 수가 여러 번 등장하지 않는 경우의 수
     * 중복없는 수열들을 구하면됨
     * 구한 중복없는 수열들의 길이를 전부 합하면 정답
     * 포인트는 현재까지 구한 수열에서 수를 빼는건 중복이 생길수없는걸 이용
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int[] arr = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int left = 0;
        int right = 0;
        Set<Integer> set = new HashSet<>();
        long answer = 0;

        for (; left < n; left++) {
            while (right < n) {
                if (set.contains(arr[right])) break;
                set.add(arr[right++]);
            }
            answer += right - left;
            set.remove(arr[left]);
        }

        bw.write(String.valueOf(answer));

        br.close();
        bw.close();
    }
}
