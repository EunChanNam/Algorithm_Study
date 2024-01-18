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

	static int n;
	static int[] openIdx = new int[2];
	static List<Integer> targets = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		n = Integer.parseInt(br.readLine());

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < 2; i++) {
			int open = Integer.parseInt(st.nextToken());
			openIdx[i] = open;
		}

		int iterCount = Integer.parseInt(br.readLine());
		for (int i = 0; i < iterCount; i++) {
			targets.add(Integer.parseInt(br.readLine()));
		}

		dfs(0, 0);

		bw.write(String.valueOf(answer));

		br.close();
		bw.close();
	}

	static int answer = Integer.MAX_VALUE;
	private static void dfs(int level, int sum) {
		if (level == targets.size()) {
			answer = Math.min(answer, sum);
			return;
		}

		int open1 = openIdx[0];
		int dis1 = swapAndGetDis(targets.get(level), 0);
		dfs(level + 1, sum + dis1);
		swapAndGetDis(open1, 0);

		int open2 = openIdx[1];
		int dis2 = swapAndGetDis(targets.get(level), 1);
		dfs(level + 1, sum + dis2);
		swapAndGetDis(open2, 1);
	}

	private static int swapAndGetDis(int target, int idx) {
		int dis = Math.abs(openIdx[idx] - target);
		openIdx[idx] = target;
		return dis;
	}
}