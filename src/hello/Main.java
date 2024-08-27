package hello;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

    private static int n;
    private static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        map = new int[n][n];

        for (int y = 0; y < n; y++) {
            st = new StringTokenizer(br.readLine());
            for (int x = 0; x < n; x++) {
                map[y][x] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(map, 0, 0);

        bw.write(String.valueOf(max));

        br.close();
        bw.close();
    }

    private static int max = Integer.MIN_VALUE;
    private static void dfs(int[][] map, int dir, int level) {
        int[][] tempMap = copyMap(map);
        if (level != 0) {
            move(dir, tempMap);
        }

        if (level == 5) {
            for (int y = 0; y < n; y++) {
                for (int x = 0; x < n; x++) {
                    max = Math.max(max, tempMap[y][x]);
                }
            }
            return;
        }

        for (int i = 1; i <= 4; i++) {
            dfs(tempMap, i, level + 1);
        }
    }

    private static int[][] copyMap(int[][] origin) {
        int[][] temp = new int[n][n];
        for (int y = 0; y < n; y++) {
            System.arraycopy(origin[y], 0, temp[y], 0, n);
        }
        return temp;
    }

    private static void move(int dir, int[][] map) {
        if (dir == 1) {
            for (int x = 0; x < n; x++) {
                for (int i = 0; i < n; i++) {
                    if (isZero(map[i][x])) {
                        continue;
                    }
                    for (int j = i + 1; j < n; j++) {
                        if (xMergeIf(map, x, i, j)) {
                            continue;
                        }
                        break;
                    }
                }
            }
            for (int x = 0; x < n; x++) {
                for (int i = 0; i < n; i++) {
                    for (int j = i + 1; j < n; j++) {
                        swapIf(i, x, j, x, map);
                    }
                }
            }
        } else if (dir == 2) {
            for (int x = 0; x < n; x++) {
                for (int i = n - 1; i >= 0; i--) {
                    if (isZero(map[i][x])) {
                        continue;
                    }
                    for (int j = i - 1; j >= 0; j--) {
                        if (xMergeIf(map, x, i, j)) {
                            continue;
                        }
                        break;
                    }
                }
            }
            for (int x = 0; x < n; x++) {
                for (int i = n - 1; i >= 0; i--) {
                    for (int j = i - 1; j >= 0; j--) {
                        swapIf(i, x, j, x, map);
                    }
                }
            }
        } else if (dir == 3) {
            for (int y = 0; y < n; y++) {
                for (int i = 0; i < n; i++) {
                    if (isZero(map[y][i])) {
                        continue;
                    }
                    for (int j = i + 1; j < n; j++) {
                        if (yMergeIf(map, y, i, j)) {
                            continue;
                        }
                        break;
                    }
                }
            }
            for (int y = 0; y < n; y++) {
                for (int i = 0; i < n; i++) {
                    for (int j = i + 1; j < n; j++) {
                        swapIf(y, i, y, j, map);
                    }
                }
            }
        } else if (dir == 4) {
            for (int y = 0; y < n; y++) {
                for (int i = n - 1; i >= 0; i--) {
                    if (isZero(map[y][i])) {
                        continue;
                    }
                    for (int j = i - 1; j >= 0; j--) {
                        if (yMergeIf(map, y, i, j)) {
                            continue;
                        }
                        break;
                    }
                }
            }
            for (int y = 0; y < n; y++) {
                for (int i = n - 1; i >= 0; i--) {
                    for (int j = i - 1; j >= 0; j--) {
                        swapIf(y, i, y, j, map);
                    }
                }
            }
        }
    }

    private static boolean isZero(int value) {
        return value == 0;
    }

    private static boolean yMergeIf(int[][] map, int y, int i, int j) {
        int now = map[y][i];
        int next = map[y][j];
        if (next == 0) return true;
        if (now == next) {
            map[y][i] += map[y][j];
            map[y][j] = 0;
        }
        return false;
    }

    private static boolean xMergeIf(int[][] map, int x, int i, int j) {
        int now = map[i][x];
        int next = map[j][x];
        if (next == 0) return true;
        if (now == next) {
            map[i][x] += map[j][x];
            map[j][x] = 0;
        }
        return false;
    }

    private static void swapIf(int y, int x, int ny, int nx, int[][] map) {
        if (map[y][x] == 0 && map[ny][nx] != 0) {
            int temp = map[y][x];
            map[y][x] = map[ny][nx];
            map[ny][nx] = temp;
        }
    }
}
