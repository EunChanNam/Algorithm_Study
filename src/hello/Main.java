package hello;
import java.io.*;
import java.util.*;

public class Main {
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int n;
    static List<Person> list = new ArrayList<>();
    static int[] v;
    static class Person{
        Integer k;
        Integer w;
        public Person(int k, int w) {
            this.k = k;
            this.w = w;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());
        v = new int[n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            list.add(new Person(a, b));
        }

        Comparator<Person> comparator = (a, b) -> {
            if (!a.k.equals(b.k)) return a.k.compareTo(b.k);
            else return a.w.compareTo(b.w);
        };
        list.sort(comparator.reversed());

        int cnt =0;
        int max = Integer.MIN_VALUE;
        for (Person p : list) {
            if (p.w > max) {
                max = p.w;
                cnt++;
            }
        }

        System.out.println(cnt);
    }

}