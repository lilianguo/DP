class BestTimeToBuyAndSellStock{
    public int maxProfit(int[] A) {
        // write your code here
        // dp[i] 表示第i天卖的话，收益最大是多少
        // dp[i] = dp[i - 1] + (A[i] - A[i - 1]) || A[i] - A[i - 1]
        // 前者表示买入发生在i - 1之前
        //后者表示买入发生在i - 1
        
        if (A.length == 0) {
            return 0;
        }
        int res = 0;
        int[] dp = new int[A.length];
        for (int i = 1; i < A.length; i++) {
            dp[i] = Math.max(dp[i - 1] + A[i] - A[i - 1], A[i] - A[i - 1]);
            res = Math.max(res, dp[i]);
        }
        return res;
    }
} 