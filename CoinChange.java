/*
https://leetcode.com/problems/coin-change/

322. Coin Change
Medium

You are given coins of different denominations and a total amount of money amount. 
Write a function to compute the fewest number of coins that you need to make up that amount. 
If that amount of money cannot be made up by any combination of the coins, return -1.

Example 1:

Input: coins = [1, 2, 5], amount = 11
Output: 3 
Explanation: 11 = 5 + 5 + 1
Example 2:

Input: coins = [2], amount = 3
Output: -1
Note:
You may assume that you have an infinite number of each kind of coin.
dp[i][j]=max(dp[i−1][j],dp[i−1][j−k∗coin[i]]+k)(0≤k∗coin[i]≤j)

min(dp[i−1][j−k∗coin[i]]+k) = 
                        dp[i - 1][j - coins[i]] + 1
*/
class CoinChange {
    // https://www.youtube.com/watch?v=htdBJul3xoc
    // coins是 unique且都只能用一次的
    // Time: O(n)
    // 确定使用当前coin，则最小的使用当前coin的组合为 ：
    // 1 + 组成（amount - coin) 的最小组合数
    //不断更新min
    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        for (int i = 1; i <= amount; i++) {
            dp[i] = Integer.MAX_VALUE;
            for (int j = 0; j < coins.length; j++) {
                if (i >= coins[j] && dp[i - coins[j]] != Integer.MAX_VALUE) {
                    dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1);
                }
            }
        }
        return dp[amount] == Integer.MAX_VALUE ? -1 : dp[amount];
    }

    public int coinChange() {

    }
}