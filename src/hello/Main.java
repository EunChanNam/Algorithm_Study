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

	static List<List<Integer>> top = new ArrayList<>();
	static int n;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		for (int i = 0; i < 4; i++) {
			top.add(new ArrayList<>());
			String s = br.readLine();
			for (int j = 0; j < 8; j++) {
				char ch = s.charAt(j);
				top.get(i).add(ch - '0');
			}
		}

		n = Integer.parseInt(br.readLine());

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			int targetId = Integer.parseInt(st.nextToken()) - 1;
			int dir = Integer.parseInt(st.nextToken());

			run(targetId, dir, 0);
		}

		int sum = 0;
		for (int i = 0; i < 4; i++) {
			List<Integer> target = top.get(i);
			if (i == 0 && target.get(0) == 1) {
				sum += 1;
			}
			if (i == 1 && target.get(0) == 1) {
				sum += 2;
			}
			if (i == 2 && target.get(0) == 1) {
				sum += 4;
			}
			if (i == 3 && target.get(0) == 1) {
				sum += 8;
			}
		}

		bw.write(String.valueOf(sum));

		br.close();
		bw.close();
	}

	static void run(int targetId, int dir, int flow) {
		List<Integer> target = top.get(targetId);
		int right = target.get(2);
		int left = target.get(6);

		int leftId = targetId - 1;
		if (leftId >= 0 && flow != 1) {
			List<Integer> leftTarget = top.get(leftId);
			int leftRight = leftTarget.get(2);
			if (leftRight != left) {
				run(leftId, -dir, -1);
			}
		}

		int rightId = targetId + 1;
		if (rightId < 4 && flow != -1) {
			List<Integer> rightTarget = top.get(rightId);
			int rightLeft = rightTarget.get(6);
			if (rightLeft != right) {
				run(rightId, -dir, 1);
			}
		}

		spin(dir, target);
	}

	static void spin(int dir, List<Integer> target) {
		if (dir == 1) {
			int lastIndex = target.size() - 1;
			int last = target.get(lastIndex);
			target.remove(lastIndex);
			target.add(0, last);
		}
		else {
			int lastIndex = target.size() - 1;
			int first = target.get(0);
			target.remove(0);
			target.add(lastIndex, first);
		}
	}
}