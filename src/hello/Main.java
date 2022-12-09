package hello;
import java.io.*;
import java.util.*;

public class Main {
    static int[][] dir = {{-1,0}, {0,-1}, {0,1}, {1,0}};
    static int[][] visit;
    static Queue<Point> que = new LinkedList<>();
    static class Point{
        int y; int x; int level;
        public Point(int y, int x, int level){
            this.y = y;
            this.x = x;
            this.level = level;
        }
    }

    static boolean isUnable(Point start, String[] map) {
        que.offer(start);
        visit[start.y][start.x] = 1;

        while(!que.isEmpty()){
            Point p = que.poll();
            if (p.level == 2) continue;
            for (int i=0; i < 4; i++){
                int ny = p.y + dir[i][0];
                int nx = p.x + dir[i][1];
                if (ny < 0 || ny >= 5 || nx < 0 || nx >= 5) continue;
                if (visit[ny][nx] == 1) continue;
                if (map[ny].charAt(nx) == 'X') continue;
                if (map[ny].charAt(nx) == 'P') {
                    que.clear();
                    return true;
                }
                visit[ny][nx] = 1;
                que.offer(new Point(ny, nx, p.level + 1));
            }
        }
        return false;
    }

    public static int[] solution(String[][] place) {
        int[] answer = new int[place.length];
        int n = place.length;

        for (int z=0; z < n; z++){
            boolean flag = false;
            for (int y=0; y < 5; y++){
                for (int x=0; x < 5; x++){
                    if (place[z][y].charAt(x) == 'P'){
                        visit = new int[5][5];
                        if (isUnable(new Point(y, x, 0), place[z])){
                            flag = true;
                            break;
                        }
                    }
                }
                if (flag) break;
            }
            if (flag) answer[z] = 0;
            else answer[z] = 1;
        }

        return answer;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[][] place = {{"POOOP", "OXXOX", "OPXPX", "OOXOX", "POXXP"},
                {"POOPX", "OXPXP", "PXXXO", "OXXXO", "OOOPP"},
                {"PXOPX", "OXOXP", "OXPOX", "OXXOP", "PXPOX"},
                {"OOOXX", "XOOOX", "OOOXX", "OXOOX", "OOOOO"},
                {"PXPXP", "XPXPX", "PXPXP", "XPXPX", "PXPXP"}};
        int[] solution = solution(place);

        for (int answer : solution) {
            System.out.print(answer + " ");
        }
    }
}
