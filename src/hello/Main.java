package hello;
import java.util.*;

public class Main {
    static class Point {
        Integer x;
        Integer y;
        public Point(Integer x, Integer y) {
            this.x = x;
            this.y = y;
        }
    }
    public static List<Point> solution(int n, int[][] a) {
        List<Point> answer = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            Point point = new Point(a[i][0], a[i][1]);
            answer.add(point);
        }

        Comparator<Point> comparator = (p1, p2) -> {
            if (p1.x.equals(p2.x)) {
                return p1.y.compareTo(p2.y);
            } else return p1.x.compareTo(p2.x);
        };

        answer.sort(comparator);

        return answer;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n1 = sc.nextInt();
//        int n2 = sc.nextInt();
        int[][] a = new int[n1][2];
        for (int y = 0; y < n1; y++) {
            for (int x=0; x< 2; x++){
                a[y][x] = sc.nextInt();
            }
        }
//        String str1 = sc.next();
//        String str2 = sc.next();
//        int[] a = new int[n1];
//        for (int i = 0; i < n1; i++) {
//            a[i] = sc.nextInt();
//        }
//        int[] b = new int[n2];
//        for (int i = 0; i < n2; i++) {
//            b[i] = sc.nextInt();
//        }
        List<Point> ret = solution(n1, a);
//        ret.forEach((s) -> System.out.print(s + " "));
//        for (int r : ret) {
//            System.out.print(r + " ");
//        }
//        System.out.println(ret);
        ret.forEach(p -> System.out.println(p.x + " " + p.y));
    }
}
