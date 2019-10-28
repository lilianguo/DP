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

动态规划.

设定状态: f[i] 表示字符串前i位有多少种解码方案

状态转移方程:

初始化 f 数组为 0
若字符串中 s[i] 表示的阿拉伯数字在 1~9 范围内, f[i] += f[i-1]
若s[i-1]和s[i]连起来表示的数字在 10~26 范围内, f[i] += f[i-2] (若i==1, 则f[i] += 1)
边界: f[0] = 1

特判:

如果字符串以 '0' 开头, 则直接返回0.
如果运算中发现 f[i] == 0, 则说明此处无法解码, 同样直接返回0.
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
            int twoDigits = (s.charAt(i - 2) - '0') * 10 + s.charAt(i - 1) - '0';
            if (twoDigits >= 10 && twoDigits <= 26) {
                nums[i] += nums[i - 2];
            }
        }
        return nums[s.length()];
    }
}