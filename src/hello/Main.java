package hello;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {

	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

	static int n;
	static int[] people;
	static List<List<Integer>> net = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		n = Integer.parseInt(br.readLine());
		people = new int[n + 1];
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= n; i++) {
			people[i] = Integer.parseInt(st.nextToken());
		}

		for (int i = 0; i <= n; i++) {
			net.add(new ArrayList<>());
		}

		for (int i = 1; i <= n; i++) {
			st = new StringTokenizer(br.readLine());
			int count = Integer.parseInt(st.nextToken());
			for (int j = 0; j < count; j++) {
				net.get(i).add(Integer.parseInt(st.nextToken()));
			}
		}

		dfs(1);

		if (answer == Integer.MAX_VALUE) {
			bw.write("-1");
		} else {
			bw.write(String.valueOf(answer));
		}

		br.close();
		bw.close();
	}

	static Stack<Integer> area1 = new Stack<>();
	static Stack<Integer> area2 = new Stack<>();
	static int answer = Integer.MAX_VALUE;
	private static void dfs(int level) {
		if (level == n + 1) {
			if (area1.isEmpty() || area2.isEmpty()) {
				return;
			}

			int result1 = bfs(area1);
			int result2 = bfs(area2);

			if (result1 == -1 || result2 == -1) {
				return;
			}

			answer = Math.min(answer, Math.abs(result1 - result2));
			return;
		}

		area1.push(level);
		dfs(level + 1);
		area1.pop();

		area2.push(level);
		dfs(level + 1);
		area2.pop();
	}

	private static int bfs(Stack<Integer> area) {
		Set<Integer> areaSet = new HashSet<>(area);
		int totalPeople = 0;

		int start = area.peek();
		boolean[] visit = new boolean[n + 1];
		visit[start] = true;
		Queue<Integer> que = new LinkedList<>();
		que.offer(start);

		while (!que.isEmpty()) {
			int now = que.poll();
			totalPeople += people[now];

			List<Integer> nowNet = net.get(now);
			for (int next : nowNet) {
				if (visit[next]) continue;
				if (!areaSet.contains(next)) continue;
				visit[next]	= true;
				que.add(next);
			}
		}

		for (int target : area) {
			if (!visit[target]) {
				return -1;
			}
		}

		return totalPeople;
	}
}