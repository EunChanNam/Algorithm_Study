package hello;
import java.util.*;

public class Main {
    static class Person{
        int id;
        int age;
        public Person(int id, int age) {
            this.id = id;
            this.age = age;
        }
    }

    public static int solution(int n, int k, int[] a) {
        int answer = 0;

        Queue<Person> que = new LinkedList<>();
        for (int i = 0; i < a.length; i++) {
            que.offer(new Person(i, a[i]));
        }

        int cnt =0;
        while (true) {
            Person p = que.poll();
            boolean flag = true;
            for (Person t : que) {
                if (t.age > p.age) {
                    que.offer(p);
                    flag = false;
                    break;
                }
            }
            if (flag){
                cnt++;
                if (p.id == k) break;
            }
        }
        answer = cnt;
        return answer;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n1 = sc.nextInt();
        int n2 = sc.nextInt();
//        int[][] a = new int[n1][n1];
//        for (int y = 0; y < n1; y++) {
//            for (int x=0; x< n1; x++){
//                a[y][x] = sc.nextInt();
//            }
//        }
//        String str1 = sc.next();
//        String str2 = sc.next();
        int[] a = new int[n1];
        for (int i = 0; i < n1; i++) {
            a[i] = sc.nextInt();
        }
//        int[] b = new int[n2];
//        for (int i = 0; i < n2; i++) {
//            b[i] = sc.nextInt();
//        }
        int ret = solution(n1, n2, a);
//        ret.forEach((s) -> System.out.print(s + " "));
        System.out.println(ret);
    }
}
