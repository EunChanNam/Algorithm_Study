package hello;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

	static class SpinEvent {
		int targetId;
		int dir;

		public SpinEvent(int targetId, int dir) {
			this.targetId = targetId;
			this.dir = dir;
		}
	}

	static List<List<Integer>> top = new ArrayList<>();
	static int n;
	static Queue<SpinEvent> spinQueue = new LinkedList<>();

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

			spinQueue.offer(new SpinEvent(targetId, dir));
			run(targetId, dir);

			while (!spinQueue.isEmpty()) {
				SpinEvent event = spinQueue.poll();
				spin(event.dir, top.get(event.targetId));
			}
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

	static void run(int targetId, int dir) {
		int leftDir = dir;
		int rightDir = dir;
		for (int i = targetId; i >= 1; i--) {
			int leftId = i - 1;
			leftDir = -leftDir;
			List<Integer> now = top.get(i);
			List<Integer> left = top.get(leftId);
			if (!now.get(6).equals(left.get(2))) {
				spinQueue.offer(new SpinEvent(leftId, leftDir));
			} else break;
		}

		for (int i = targetId; i < 3; i++) {
			int rightId = i + 1;
			rightDir = -rightDir;
			List<Integer> now = top.get(i);
			List<Integer> right = top.get(rightId);
			if (!now.get(2).equals(right.get(6))) {
				spinQueue.offer(new SpinEvent(rightId, rightDir));
			} else break;
		}
	}

	static void spin(int dir, List<Integer> target) {
		if (dir == 1) {
			int last = target.get(7);
			target.remove(target.size() - 1);
			target.add(0, last);
		}
		else {
			int first = target.get(0);
			target.remove(0);
			target.add(7, first);
		}
	}
}