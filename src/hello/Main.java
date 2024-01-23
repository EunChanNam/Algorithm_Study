package hello;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

	static int n;
	static int m;
	static int[][] map;
	static int[][] breakMap;
	static int[][] dir = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};

	static class Node{
		int y;
		int x;
		int breakCnt;

		public Node(int y, int x, int breakCnt) {
			this.y = y;
			this.x = x;
			this.breakCnt = breakCnt;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		m = Integer.parseInt(st.nextToken());
		n = Integer.parseInt(st.nextToken());
		map = new int[n][m];
		breakMap = new int[n][m];

		for (int y = 0; y < n; y++) {
			String str = br.readLine();
			for (int x = 0; x < m; x++) {
				map[y][x] = str.charAt(x) - '0';
			}
		}

		int answer = bfs(new Node(0, 0, 0));

		bw.write(String.valueOf(answer));

		br.close();
		bw.close();
	}

	static int bfs(Node start) {
		boolean[][][] visit = new boolean[10001][n][m];
		visit[0][start.y][start.x] = true;
		visit[1][start.y][start.x] = true;
		int min = Integer.MAX_VALUE;

		Queue<Node> que = new LinkedList<>();
		que.offer(start);

		while (!que.isEmpty()) {
			Node now = que.poll();
			if (now.y == n - 1 && now.x == m - 1) {
				min = Math.min(min, now.breakCnt);
			}

			for (int i = 0; i < 4; i++) {
				int ny = now.y + dir[i][0];
				int nx = now.x + dir[i][1];
				if (ny < 0 || nx < 0 || ny >= n || nx >= m) continue;
				if (map[ny][nx] == 1) {
					if (checkIsVisit(now.breakCnt + 1, visit, ny, nx)) continue;
					visit[now.breakCnt + 1][ny][nx] = true;
					que.offer(new Node(ny, nx, now.breakCnt + 1));
				} else {
					if (checkIsVisit(now.breakCnt, visit, ny, nx)) continue;
					visit[now.breakCnt][ny][nx] = true;
					que.offer(new Node(ny, nx, now.breakCnt));
				}
			}
		}

		return min;
	}

	static boolean checkIsVisit(int breakCount, boolean[][][] visit, int ny, int nx) {
		for (int i = breakCount; i >= 0; i--) {
			if (visit[i][ny][nx]) {
				return true;
			}
		}
		return false;
	}
}