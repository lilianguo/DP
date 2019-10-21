/*
91. Decode Ways
Medium

1772

2003

Favorite

Share
A message containing letters from A-Z is being encoded to numbers using the following mapping:

'A' -> 1
'B' -> 2
...
'Z' -> 26
Given a non-empty string containing only digits, determine the total number of ways to decode it.

Example 1:

Input: "12"
Output: 2
Explanation: It could be decoded as "AB" (1 2) or "L" (12).
Example 2:

Input: "226"
Output: 3
Explanation: It could be decoded as "BZ" (2 26), "VF" (22 6), or "BBF" (2 2 6).


https://www.jiuzhang.com/solution/decode-ways/
*/

class DecodeWays {
    public int numDecodings(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int[] nums = new int[s.length() + 1];
        nums[0] = 1;
        nums[1] = s.charAt(0) != '0' ? 1 : 0;
        for (int i = 2; i <= s.length(); i++) {
            // 0无法单个算作decode
            if (s.charAt(i - 1) != '0') {
                nums[i] = nums[i - 1];
            }
            int twoDigit = (s.charAt(i - 2) - '0') * 10 + s.charAt(i - 1) - '0';
            if (twoDigits >= 10 && twoDigits <= 26) {
                nums[i] += nums[i - 2];
            }
        }
        return nums[s.length()];
    }
}