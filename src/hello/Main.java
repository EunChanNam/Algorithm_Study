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

	static class Node{
		int next;
		int weight;

		public Node(int next, int weight) {
			this.next = next;
			this.weight = weight;
		}
	}

	static int landCnt;
	static int bridgeCnt;
	static List<List<Node>> list = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		landCnt = Integer.parseInt(st.nextToken());
		bridgeCnt = Integer.parseInt(st.nextToken());

		for (int i = 0; i <= landCnt; i++) {
			list.add(new ArrayList<>());
		}

		for (int i = 0; i < bridgeCnt; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			list.get(a).add(new Node(b, weight));
			list.get(b).add(new Node(a, weight));
		}

		st = new StringTokenizer(br.readLine());
		int start = Integer.parseInt(st.nextToken());
		int end = Integer.parseInt(st.nextToken());

		int answer = Integer.MIN_VALUE;
		int low = 1;
		int high = 1000000000;
		while (low <= high) {
			int mid = (low + high) / 2;

			boolean isAble = bfs(start, end, mid);
			if (isAble) {
				low = mid + 1;
				answer = Math.max(answer, mid);
			} else {
				high = mid - 1;
			}
		}

		bw.write(String.valueOf(answer));

		br.close();
		bw.close();
	}

	static boolean bfs(int start, int end, int minWeight) {
		boolean[] visit = new boolean[landCnt + 1];
		visit[start] = true;

		Queue<Node> que = new LinkedList<>();
		que.offer(new Node(start, 1000000000));

		while (!que.isEmpty()) {
			Node now = que.poll();
			if (now.next == end) {
				return true;
			}

			for (Node nextNode : list.get(now.next)) {
				int next = nextNode.next;
				int nextWeight = Math.min(now.weight, nextNode.weight);

				if (visit[next] || nextWeight < minWeight) {
					continue;
				}

				visit[next] = true;
				que.offer(new Node(next, nextWeight));
			}
		}

		return false;
	}
}