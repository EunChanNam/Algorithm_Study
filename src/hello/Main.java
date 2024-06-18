class Solution {

    public String longestPalindrome(String s) {

        for (int i=0; i < s.length(); i++) {
            getMaxLengthStr(s, i, i + 1);
            getMaxLengthStr(s, i, i + 2);
        }

        return s.substring(maxLeft, maxRight + 1);
    }

    private int maxLeft = 0;
    private int maxRight = 0;
    private int maxLength = 0;

    private void getMaxLengthStr(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {

            int length = right - left;
            if (length > maxLength) {
                maxLeft = left;
                maxRight = right;
                maxLength = length;
            }

            left--;
            right++;
        }
    }
}
