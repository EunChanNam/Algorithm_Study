import java.util.*;
class Solution {
    public String orderlyQueue(String s, int k) {
        if (k == 1) {
            List<String> resultList = findByBfs(s, k);
            resultList.sort((a, b) -> a.compareTo(b));
            return resultList.get(0);
        } else {
            char[] charArray = s.toCharArray();
            Arrays.sort(charArray);
            return String.valueOf(charArray);
        }
    }

    private List<String> findByBfs(String s, int k) {
        int n = s.length();

        List<String> result = new ArrayList<>();
        String now = s;
        for (int i=0; i < n; i++) {
            StringBuilder sb = new StringBuilder(now);

            char first = now.charAt(0);
            sb.deleteCharAt(0);
            sb.append(first);

            now = sb.toString();
            result.add(now);
        }

        return result;
    }
}
