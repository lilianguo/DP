/*
161. One Edit Distance
Medium

468

91

Favorite

Share
Given two strings s and t, determine if they are both one edit distance apart.

Note: 

There are 3 possiblities to satisify one edit distance apart:

Insert a character into s to get t
Delete a character from s to get t
Replace a character of s to get t
Example 1:

Input: s = "ab", t = "acb"
Output: true
Explanation: We can insert 'c' into s to get t.
Example 2:

Input: s = "cab", t = "ad"
Output: false
Explanation: We cannot get t from s by only one step.
Example 3:

Input: s = "1203", t = "1213"
Output: true
Explanation: We can replace '0' with '1' to get t.
*/


/*
可以用dp算出min distance也可以直接判断是不是只有一位不同

*/
class OneEditDistance {
    public boolean isOneEditDistance(String word1, String word2) {
        int m = word1.length(), n = word2.length();
        if (m * n == 0) {
            return m == 1 ||  n == 1;
        }

        if (m == n) {
            int count = 0;
            for (int i = 0; i < m; i++) {
                if (word1.charAt(i) != word2.charAt(i)) {
                    count++;
                }
            }
            return count == 1;
        }
        if (m != n) {
            if (m > n) {
                return isValid(word1, word2);
            } else {
                return isValid(word2, word1);
            }
        }
        return true;
    }
    
    private boolean isValid(String l, String s) {
        int idx = 0, m = l.length(), n = s.length();
        if (m - n > 1) {
            return false;
        }
        for (int i = 0; i < n; i++) {
            if (l.charAt(i) != s.charAt(i)) {
                idx = i;
                break;
            }
            if (i == n - 1) {
                return true;
            }
        }
        for (int j = idx + 1; j < m; j++) {
            if (l.charAt(j) != s.charAt(j - 1)) {
                return false;
            }
        }
        return true;
    }
}