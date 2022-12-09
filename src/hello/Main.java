package hello;
import java.io.*;
import java.util.*;

public class Main {
    public int solution(int[] a) {
        int answer = 0;
        int n = a.length;

        Stack<Integer> stack = new Stack<>();
        int target = 0;
        for (int i = 1; i <= n; i++){
            if (i == a[target]){
                answer++;
                target++;
                while(!stack.isEmpty()){
                    int p = stack.peek();
                    if (p == a[target]){
                        answer++;
                        target++;
                        stack.pop();
                    } else break;
                }
            } else stack.push(i);
        }


        return answer;
    }

    public static void main(String[] args) {

    }
}
