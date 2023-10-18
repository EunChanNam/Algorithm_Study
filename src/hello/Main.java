package hello;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class Main {

    static class Node {
        String type;
        int coin;
        List<Integer> doorNumbers;

        public Node(String type, int value, List<Integer> doorNumbers) {
            this.type = type;
            this.coin = value;
            this.doorNumbers = doorNumbers;
        }
    }

    static class Target {
        int coin;
        int number;

        public Target(int coin, int number) {
            this.coin = coin;
            this.number = number;
        }
    }

    static List<Node> nodes = new ArrayList<>();

    static boolean flag = false;
    static boolean[] visit;
    static void dfs(int n, Target now) {
        if (flag) return;

        Node node = nodes.get(now.number);
        if (now.number == n - 1) {
            if (now.coin < node.coin) return;
            flag = true;
            return;
        }

        int nextCoin = now.coin;
        if (node.type.equals("T")) {
            if (now.coin < node.coin) return;
            nextCoin -= node.coin;
        }
        if (node.type.equals("L") && now.coin < node.coin) {
            nextCoin = node.coin;
        }

        for (int doorNumber : node.doorNumbers) {
            if (visit[doorNumber - 1]) continue;

            visit[doorNumber - 1] = true;
            dfs(n, new Target(nextCoin, doorNumber - 1));
            visit[doorNumber - 1] = false;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        // StringTokenizer st = new StringTokenizer(br.readLine());

        while (true) {
            int n = Integer.parseInt(br.readLine());
            if (n == 0) {
                break;
            }

            for (int i = 0; i < n; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                String type = st.nextToken();
                int value = Integer.parseInt(st.nextToken());
                List<Integer> doorNumbers = new ArrayList<>();
                while (true) {
                    int doorNumber = Integer.parseInt(st.nextToken());
                    if (doorNumber == 0) break;
                    doorNumbers.add(doorNumber);
                }
                nodes.add(new Node(type, value, doorNumbers));
            }

            flag = false;
            visit = new boolean[n];
            visit[0] = true;
            dfs(n, new Target(0, 0));

            if (flag) {
                bw.write("Yes");
                bw.newLine();
            } else {
                bw.write("No");
                bw.newLine();
            }

            nodes = new ArrayList<>();
        }

        br.close();
        bw.close();

    }
}