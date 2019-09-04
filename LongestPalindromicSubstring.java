public class LongestPalinDromicSubstring {
    /**
     * @param s: input string
     * @return: the longest palindromic substring
     */
    // time: O(n^2)
    // space: O(n^2)
    // DP
    // extend from mid 2 point to left and right
    // "aaaaa" is a valid palindrom
    // "abba" is a valid palindrom
    // https://leetcode.com/problems/longest-palindromic-substring/
    public String longestPalindrome(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
        int n = s.length();
        boolean[][] isPalindromic = new boolean[n][n];

        int longest = 1, start = 0;
        for (int i=  0; i < n; i++) {
            isPalindromic[i][i] = true;
            if (i < n - 1) {
                isPalindromic[i][i + 1] = s.charAt(i) == s.charAt(i + 1);
                if (isPalindromic[i][i + 1]) {
                    longest = 2;
                    start = i;
                }
            }
        }

        for (int i = n - 1; i >= 0; i--) {
            for (int j = i + 2; j < n; j++) {
                isPalindromic[i][j] = isPalindromic[i + 1][j - 1] && s.charAt(i) == s.charAt(j);
                if (isPalindromic[i][j] && j - i + 1 > longest) {
                    longest = j - i + 1;
                    start = i;
                }
            }
        }
        return s.substring(start, start + longest);
    }
}