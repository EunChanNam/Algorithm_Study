package hello;
import java.io.*;
import java.util.*;

public class Main {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String str = br.readLine();
        Stack<Character> stack = new Stack<>();
        int n = str.length();

        boolean flag = true;
        for (int i = 0; i < n; i++) {
            char ch = str.charAt(i);
            if (ch == 'P') {
                stack.push(ch);
            } else {
                if (i != n - 1) {
                    if (str.charAt(i + 1) == 'A') {
                        flag = false;
                        break;
                    }
                } else {
                    if (ch == 'A') {
                        flag = false;
                        break;
                    }
                }
                if (stack.size() < 2) {
                    flag = false;
                    break;
                } else {
                    stack.pop();
                    stack.pop();
                }
            }
        }
        if (stack.size() != 1) flag = false;

        if (flag) bw.write("PPAP");
        else bw.write("NP");

        bw.close();
        br.close();
    }
}
