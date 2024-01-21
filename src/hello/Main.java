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

	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

	static class Point{
		int y;
		int x;
		public Point(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}

	static int n;
	static int m;
	static int h;
	static List<Point> mints = new ArrayList<>();
	static Point start;


	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		h = Integer.parseInt(st.nextToken());

		for (int y = 0; y < n; y++) {
			st = new StringTokenizer(br.readLine());
			for (int x = 0; x < n; x++) {
				int value = Integer.parseInt(st.nextToken());
				if (value == 2) mints.add(new Point(y, x));
				if (value == 1) start = new Point(y, x);
			}
		}

		visit = new boolean[mints.size()];

		dfs(0);

		System.out.println(answer);

		br.close();
		bw.close();
	}

	static Stack<Point> stack = new Stack<>();
	static boolean[] visit;
	static int answer = 0;
	static void dfs(int level) {
		if (level == mints.size()) {
			findMaxMint();
		}

		for (int i = 0; i < mints.size(); i++) {
			if (visit[i]) continue;
			visit[i] = true;
			stack.push(mints.get(i));
			dfs(level + 1);
			stack.pop();
			visit[i] = false;
		}
	}

	static void findMaxMint() {
		int nowHp = m;
		Point now = new Point(start.y, start.x);
		int cnt = 0;
		for (Point mint : stack) {
			int dis = Math.abs(mint.y - now.y) + Math.abs(mint.x - now.x);
			if (dis > nowHp){
				return;
			}
			cnt++;
			nowHp -= dis;
			nowHp += h;
			now = new Point(mint.y, mint.x);

			int homeDis = Math.abs(start.y - now.y) + Math.abs(start.x - now.x);
			if (nowHp >= homeDis) {
				answer = Math.max(answer, cnt);
			}
		}
	}
}