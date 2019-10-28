/*
639. Decode Ways II

A message containing letters from A-Z is being encoded to numbers using the following mapping way:

'A' -> 1
'B' -> 2
...
'Z' -> 26
Beyond that, now the encoded string can also contain the character '*', which can be treated as one of the numbers from 1 to 9.

Given the encoded message containing digits and the character '*', return the total number of ways to decode it.

Also, since the answer may be very large, you should return the output mod 109 + 7.

Example 1:
Input: "*"
Output: 9
Explanation: The encoded message can be decoded to the string: "A", "B", "C", "D", "E", "F", "G", "H", "I".
Example 2:
Input: "1*"
Output: 9 + 9 = 18
Note:
The length of the input string will fit in range [1, 105].
The input string will only contain the character '*' and digits '0' - '9'.
*/

/*
类似decode ways
首先确定dp[0] = 1, dp[1] 根据第一个字符来赋值

for: i = 2 -> s.length()

分两种情况：

如果当前字符为非 * , 如果非0， 则dp[i] = dp[i - 1]， 同时考虑前一个字符，根据前一个字符的不同情况来给dp[i] 加上不同倍数的dp[i - 2]

当前字符为 *， dp[i] = 9 * dp[i -1], 然后根据前一个字符的情况来对dp[i] 加上不同倍数的dp[i - 2]
*/
class DecodeWaysII {
    public int numDecodings(String s) {
        if(s == null || s.length() == 0){
            return 0;
        }
        
        long dp[] = new long[s.length() +1];
        dp[0] = 1;
        dp[1] = 0;
        if(s.charAt(0) == '*'){
            dp[1] = 9;
        }else if(s.charAt(0) != '0'){
            dp[1] = 1;
        }
        
        for(int i = 2; i <= s.length(); i++){
            if(s.charAt(i - 1) != '*'){
                if(s.charAt(i - 1) != '0'){
                    dp[i] += dp[i - 1];
                }
                
                if(s.charAt(i - 2) == '*'){
                    if(s.charAt(i - 1) > '6'){
                        dp[i] += dp[i - 2];
                    }else{
                        dp[i] += (2 * dp[i - 2]);
                    }
                }else{
                    StringBuilder sb = new StringBuilder();
                    sb.append(s.charAt(i - 2));
                    sb.append(s.charAt(i - 1));
                    int num = Integer.valueOf(sb.toString());
                    
                    if(num >= 10 && num <= 26){
                        dp[i] += dp[i - 2];
                    }
                }
                
            }else{
                dp[i] += (9 * dp[i - 1]);
                
                if(s.charAt(i - 2) == '*'){
                    dp[i] += (15 * dp[i - 2]);
                }else{
                    if(s.charAt(i - 2) == '1'){
                        dp[i] += (9 * dp[i - 2]);
                    }
                    if(s.charAt(i - 2) == '2'){
                        dp[i] += (6 * dp[i - 2]);
                    }
                }
                
            }
            dp[i] %= 1000000007;
        }

        return (int)dp[s.length()];
    }
}