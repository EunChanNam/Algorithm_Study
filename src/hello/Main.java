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
	static int[][] map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		map = new int[n][n];

		for (int y = 0; y < n; y++) {
			st = new StringTokenizer(br.readLine());
			for (int x = 0; x < n; x++) {
				map[y][x] = Integer.parseInt(st.nextToken());
			}
		}

		for (int i = 0; i < n; i++) {
			team2.add(i);
		}
		dfs(0, 0);

		bw.write(String.valueOf(answer));

		br.close();
		bw.close();
	}

	static List<Integer> team1 = new ArrayList<>();
	static List<Integer> team2 = new ArrayList<>();
	static int answer = Integer.MAX_VALUE;

	static void dfs(int level, int start) {
		if (!team1.isEmpty() && !team2.isEmpty()) {
			int team1Score = getTeamScore(team1);
			int team2Score = getTeamScore(team2);
			answer = Math.min(answer, Math.abs(team1Score - team2Score));
		}

		for (int i = start; i < n; i++) {
			team1.add(i);
			team2.remove(Integer.valueOf(i));
			dfs(level + 1, i + 1);
			team2.add(i);
			team1.remove(team1.size() - 1);
		}
	}

	static int getTeamScore(List<Integer> team) {
		int m = team.size();
		int result = 0;
		for (int y = 0; y < m; y++) {
			int a = team.get(y);
			for (int x = 0; x < m; x++) {
				if (y == x) continue;
				int b = team.get(x);
				result += map[a][b];
			}
		}

		return result;
	}
}