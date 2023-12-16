package hello;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {

	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

	static Set<Character> set = new HashSet<>();
	static int length;
	static int type;
	static List<Character> words = new ArrayList<>();

	static Stack<Character> stack = new Stack<>();
	static void search(
		int level,
		int start,
		int count1,
		int count2
	) throws IOException {
		if (level == length) {
			if (count1 < 1 || count2 < 2) return;
			StringBuilder sb = new StringBuilder();
			stack.forEach(sb::append);

			bw.write(sb.toString());
			bw.newLine();
		}

		for (int i = start; i < type; i++) {
			stack.push(words.get(i));

			if (set.contains(words.get(i))) {
				search(level + 1, i + 1, count1 + 1, count2);
			} else{
				search(level + 1, i + 1, count1, count2 + 1);
			}

			stack.pop();
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		length = Integer.parseInt(st.nextToken());
		type = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < type; i++) {
			words.add(st.nextToken().charAt(0));
		}

		words.sort(Character::compare);
		set.add('a');
		set.add('e');
		set.add('i');
		set.add('o');
		set.add('u');

		search(0, 0, 0, 0);

		br.close();
		bw.close();
	}
}