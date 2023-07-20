package hello;

public class Main {
    static int[] picks;
    static String[] minerals;

    static int min = Integer.MAX_VALUE;
    static void mine(int level, int pickIdx, int sum, int count) {
        if (level == minerals.length) {
            min = Math.min(sum, min);
            return;
        }

        int result = processMine(level, pickIdx);

        if (count > 0) {
            mine(level + 1, pickIdx, sum + result, count - 1);
            return;
        }

        if (notExistPick()) {
            min = Math.min(sum, min);
            return;
        }


        for (int i = 0; i < 3; i++) {
            if (picks[i] <= 0) continue;
            picks[i]--;
            mine(level, i, sum, 5);
            picks[i]++;
        }
    }

    private static boolean notExistPick() {
        for (int pick : picks) {
            if (pick > 0) return false;
        }
        return true;
    }

    private static int processMine(int level, int pickIdx) {
        if (pickIdx == 0) return 1;
        if (pickIdx == 1) {
            if (minerals[level].equals("diamond")) return 5;
            else return 1;
        }
        if (pickIdx == 2) {
            if (minerals[level].equals("diamond")) return 25;
            if (minerals[level].equals("iron")) return 5;
            else return 1;
        }
        throw new IllegalStateException();
    }

    static int solution(int[] p, String[] m) {
        picks = p;
        minerals = m;

        for (int i = 0; i < 3; i++) {
            if (picks[i] <= 0) continue;
            picks[i]--;
            mine(0, i, 0, 5);
            picks[i]++;
        }

        return min;
    }

    public static void main(String[] args) {
        int[] picks = {0, 1, 1};
        String[] minerals = {"diamond", "diamond", "diamond", "diamond", "diamond", "iron", "iron", "iron", "iron", "iron", "diamond"};
        System.out.println(solution(picks, minerals));
    }
}