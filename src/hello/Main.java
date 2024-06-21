import java.util.*;
class Solution {
    public long minimalKSum(int[] nums, int k) {

        Arrays.sort(nums);

        int now = 1;
        long sum = 0L;

        for (int num : nums) {
            boolean flag = false;
            while (num > now) {
                if (k <= 0){
                    flag = true;
                    break;
                }
                sum += now++;
                k--;
            }
            if (flag) break;
            now = num + 1;
        }

        while (k > 0) {
            sum += now++;
            k--;
        }

        return sum;
    }
}
