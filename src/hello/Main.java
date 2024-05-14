package hello;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {

    private static class Belt {
        int hp;

        public Belt(int hp) {
            this.hp = hp;
        }
    }
    private static class Robot {
        int idx;

        public Robot(int idx) {
            this.idx = idx;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        List<Belt> belts = new ArrayList<>();
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n * 2; i++) {
            belts.add(new Belt(Integer.parseInt(st.nextToken())));
        }

        List<Robot> robots = new ArrayList<>();
        int beltFinCnt = 0;
        int round = 0;
        while (true) {
            // 1
            runBelt(belts);
            runRobot(robots, n);
            // 2
            Set<Integer> robotIdxSet = new HashSet<>();
            for (int i = robots.size() - 1; i >= 0; i--) {
                Robot robot = robots.get(i);
                int nextIdx = robot.idx + 1;

                if (belts.get(nextIdx).hp > 0 && !robotIdxSet.contains(nextIdx)) {
                    robotIdxSet.add(nextIdx);
                    belts.get(nextIdx).hp--;
                    robot.idx++;

                    if (belts.get(nextIdx).hp == 0) {
                        beltFinCnt++;
                    }
                } else {
                    robotIdxSet.add(robot.idx);
                }
            }
            if (!robots.isEmpty()) {
                Robot last = robots.get(robots.size() - 1);
                if (last.idx == n - 1) {
                    robots.remove(robots.size() - 1);
                }
            }

            // 3
            Belt start = belts.get(0);
            if (start.hp > 0) {
                robots.add(0, new Robot(0));
                start.hp--;

                if (start.hp == 0) {
                    beltFinCnt++;
                }
            }

            // 4
            round++;
            if (beltFinCnt >= m) {
                break;
            }
        }

        bw.write(String.valueOf(round));

        br.close();
        bw.close();
    }

    private static void runRobot(List<Robot> robots, int n) {
        if (robots.isEmpty()) {
            return;
        }
        robots.forEach(robot -> robot.idx++);
        Robot last = robots.get(robots.size() - 1);
        if (last.idx == n - 1) {
            robots.remove(robots.size() - 1);
        }
    }

    private static void runBelt(List<Belt> belts) {
        Belt last = belts.get(belts.size() - 1);
        belts.remove(belts.size() - 1);
        belts.add(0, last);
    }
}
