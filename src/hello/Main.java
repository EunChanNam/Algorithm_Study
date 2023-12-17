package hello;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

	static int[][] dir = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

	static int getNextDir(int nowDir) {
		if (nowDir == 0) return 3;
		return nowDir - 1;
	}

	static int getBackDir(int nowDir) {
		return getNextDir(getNextDir(nowDir));
	}
	/**
	 * 1. 상하좌우에서 빈칸이 있는지 확인
	 * 2. 없다면 현재 방향 반대 방향으로 후진 -> 후진 불가능하면 게임 종료
	 * 3. 있다면 방향을 반시계 방향으로 회전
	 * 4. 회전한 방향에 빈칸이 있다면 이동 -> 회전 방향에 빈칸이 없다면 다시 회전
	 * 5. 청소를 진행하고 다시 1번으로 이동
	 */

	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

	static int n;
	static int m;
	static int[][] map;

	static int solution(int startY, int startX, int startDir) {
		int nowY = startY;
		int nowX = startX;
		int nowDir = startDir;
		int count = 0;

		while (true) {
			//청소
			if (map[nowY][nowX] == 0) {
				count++;
				map[nowY][nowX] = -1;
			}

			//1
			boolean flag = false;
			for (int i = 0; i < 4; i++) {
				int ny = nowY + dir[i][0];
				int nx = nowX + dir[i][1];
				if (ny < 0 || nx < 0 || ny >= n || nx >= m) continue;
				if (map[ny][nx] == 0) {
					flag = true;
					break;
				}
			}

			//2
			if (!flag) {
				int backDir = getBackDir(nowDir);
				int ny = nowY + dir[backDir][0];
				int nx = nowX + dir[backDir][1];
				if (ny < 0 || nx < 0 || ny >= n || nx >= m) {
					break;
				}
				if (map[ny][nx] == 1) {
					break;
				}
				nowY = ny;
				nowX = nx;
				continue;
			}

			//3
			nowDir = getNextDir(nowDir);
			int ny = nowY + dir[nowDir][0];
			int nx = nowX + dir[nowDir][1];
			if (ny < 0 || nx < 0 || ny >= n || nx >= m) {
				continue;
			}

			//4
			if (map[ny][nx] == 0) {
				nowY = ny;
				nowX = nx;
			}
		}

		return count;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		int startY = Integer.parseInt(st.nextToken());
		int startX = Integer.parseInt(st.nextToken());
		int startDir = Integer.parseInt(st.nextToken());

		map = new int[n][m];
		for (int y = 0; y < n; y++) {
			st = new StringTokenizer(br.readLine());
			for (int x = 0; x < m; x++) {
				map[y][x] = Integer.parseInt(st.nextToken());
			}
		}

		int answer = solution(startY, startX, startDir);
		bw.write(String.valueOf(answer));

		br.close();
		bw.close();
	}
}