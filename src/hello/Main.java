package hello;

public class Main {

    class Solution {

        int n;
        int[] apeach;
        int[] lion;
        int[] resultLion;
        int result = Integer.MIN_VALUE;

        public void copy(){
            for (int i=0; i < lion.length; i++){
                resultLion[i] = lion[i];
            }
        }
        public void dfs(int level){
            if (level == n){
                int scoreDiff = getScoreDiff(apeach, lion);
                if (scoreDiff >= result){
                    result = scoreDiff;
                    copy();
                }
                return;
            }

            for (int i=0; i < lion.length; i++){
                if (lion[i] > apeach[i]) continue;
                int plus = apeach[i] - lion[i] + 1;
                if (plus + level > n) {
                    plus = n - level;
                }
                lion[i] += plus;
                dfs(level + plus);
                lion[i] -= plus;
            }
        }

        public int[] solution(int m, int[] info) {
            int[] answer = new int[info.length];
            n = m;
            apeach = info;
            lion = new int[info.length];
            resultLion = new int[info.length];
            dfs(0);

            if (result == -1 || result == 0){
                int[] loseAnswer = {-1};
                return loseAnswer;
            }

            for (int i=0; i < lion.length; i++){
                answer[i] = resultLion[i];
            }

            return answer;
        }

        public int getScoreDiff(int[] a, int[] b){
            int aSum = 0;
            int bSum = 0;
            for (int i=0; i < a.length; i++){
                if (a[i] < b[i]){
                    bSum += 10 - i;
                } else if(a[i] > 0){
                    aSum += 10 - i;
                }
            }
            if (aSum > bSum){
                return -1;
            } else {
                return bSum - aSum;
            }
        }
    }
}