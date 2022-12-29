package hello;
import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        String[] arr1 = new String[n];
        String[] arr2 = new String[m];

        for (int i = 0; i < n; i++) {
            arr1[i] = br.readLine();
        }
        for (int i = 0; i < m; i++) {
            arr2[i] = br.readLine();
        }

        int answer = 0;
        for (int y = 0; y < m; y++) {
            for (int x = 0; x < n; x++) {
                if (arr2[y].equals(arr1[x])) answer++;
            }
        }

        bw.write(String.valueOf(answer));

        br.close();
        bw.close();
    }
}
