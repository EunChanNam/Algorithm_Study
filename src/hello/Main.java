package hello;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {

    private static class Building {

        int start;
        int height;

        public Building(int start, int height) {
            this.start = start;
            this.height = height;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        List<Building> buildings = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            buildings.add(new Building(a, b));
        }

        int cnt = 0;
        Stack<Integer> stack = new Stack<>();
        for (Building building : buildings) {
            while (!stack.isEmpty()) {
                int top = stack.peek();
                if (top > building.height) {
                    stack.pop();
                } else {
                    break;
                }
            }

            boolean flag = false;
            for (int height : stack) {
                if (height == building.height) {
                    flag = true;
                    break;
                }
            }

            if (!flag && building.height != 0) {
                cnt++;
                stack.push(building.height);
            }
        }

        bw.write(String.valueOf(cnt));

        br.close();
        bw.close();
    }
}
