package hello;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

	static int[][] dir = {{0, 1}, {0, -1}, {-1, 0}, {1, 0}};
	static int n;
	static int m;
	static int[][] map;
	static int k;
	static List<Integer> command = new ArrayList<>();

	static void rowLeft(int[][] dice) {
		int start = dice[1][0];
		dice[1][0] = dice[3][1];
		dice[3][1] = dice[1][2];
		dice[1][2] = dice[1][1];
		dice[1][1] = start;
	}

	static void rowRight(int[][] dice) {
		int start = dice[1][2];
		dice[1][2] = dice[3][1];
		dice[3][1] = dice[1][0];
		dice[1][0] = dice[1][1];
		dice[1][1] = start;
	}

	static void colUp(int[][] dice) {
		int start = dice[0][1];
		dice[0][1] = dice[3][1];
		dice[3][1] = dice[2][1];
		dice[2][1] = dice[1][1];
		dice[1][1] = start;
	}

	static void colDown(int[][] dice) {
		int start = dice[2][1];
		dice[2][1] = dice[3][1];
		dice[3][1] = dice[0][1];
		dice[0][1] = dice[1][1];
		dice[1][1] = start;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		int sy = Integer.parseInt(st.nextToken());
		int sx = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		map = new int[n][m];

		for (int y = 0; y < n; y++) {
			st = new StringTokenizer(br.readLine());
			for (int x = 0; x < m; x++) {
				map[y][x] = Integer.parseInt(st.nextToken());
			}
		}

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < k; i++) {
			command.add(Integer.parseInt(st.nextToken()) - 1);
		}

		int[][] dice = new int[4][3];

		int ny = sy;
		int nx = sx;
		for (int i = 0; i < k; i++) {
			int cmd = command.get(i);

			ny += dir[cmd][0];
			nx += dir[cmd][1];
			if (ny < 0 || nx < 0 || ny >= n || nx >= m){
				ny -= dir[cmd][0];
				nx -= dir[cmd][1];
				continue;
			}

			if (cmd == 0) {
				rowRight(dice);
				checkMapAndDice(dice, ny, nx);
			} else if (cmd == 1) {
				rowLeft(dice);
				checkMapAndDice(dice, ny, nx);
			} else if (cmd == 2) {
				colUp(dice);
				checkMapAndDice(dice, ny, nx);
			} else if (cmd == 3) {
				colDown(dice);
				checkMapAndDice(dice, ny, nx);
			}

			bw.write(String.valueOf(dice[3][1]));
			bw.newLine();
		}

		br.close();
		bw.close();
	}

	private static void checkMapAndDice(int[][] dice, int ny, int nx) {
		if (map[ny][nx] != 0) {
			dice[1][1] = map[ny][nx];
			map[ny][nx] = 0;
		} else {
			map[ny][nx] = dice[1][1];
		}
	}
}