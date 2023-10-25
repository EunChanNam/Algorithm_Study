package hello;

import java.util.*;

public class Main {

    class Solution {

        class Result {
            int cnt;
            int price;
            public Result(int cnt, int price){
                this.cnt = cnt;
                this.price = price;
            }
        }

        int[][] users;
        int[] emoticons;
        int n;
        int m;
        int[] discount = {10, 20, 30, 40};

        List<Integer> list = new ArrayList<>();

        List<Result> results = new ArrayList<>();

        void dfs(int level){
            if (level == m){
                int userCnt = 0;
                int totalPrice = 0;
                for (int i=0; i < n; i++){
                    int userDiscount = users[i][0];
                    int userPrice = users[i][1];

                    int total = 0;
                    for (int j=0; j < m; j++){
                        int dc = list.get(j);
                        if (list.get(j) >= userDiscount){
                            total += emoticons[j] * (100 - dc) / 100;
                        }
                    }
                    if (total >= userPrice){
                        userCnt++;
                    } else {
                        totalPrice += total;
                    }
                }

                results.add(new Result(userCnt, totalPrice));
                return;
            }

            for (int i=0; i < 4; i++){
                list.add(discount[i]);
                dfs(level + 1);
                list.remove(list.size() - 1);
            }
        }

        public int[] solution(int[][] u, int[] e) {
            int[] answer = new int[2];
            users = u;
            emoticons = e;
            n = users.length;
            m = e.length;

            dfs(0);

            results.sort(
                (a, b) -> {
                    if (a.cnt == b.cnt) return Integer.compare(b.price, a.price);
                    return Integer.compare(b.cnt, a.cnt);
                }
            );
            Result result = results.get(0);
            answer[0] = result.cnt;
            answer[1] = result.price;

            return answer;
        }
    }
}