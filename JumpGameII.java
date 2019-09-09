class JumpGameII {
    public boolean jump(int[] A) {
        // write your code here
        // dp[i] jump到i 所需最小步数
        // 转移 dp[i]  = min (dp[j] + 1); (j < i)
        // 不断更新dp[j]
        // 更新条件： j可达，且j能走到i
        int[] dp = new int[A.length];
        dp[0] = 0;
        for (int i = 1; i < A.length; i++) {
            dp[i] = Integer.MAX_VALUE;
            for (int j = 0; j < i; j++) {
                if (dp[j] + 1 < dp[i] && A[j] >= (i - j) ) {
                    dp[i] = dp[j] + 1;
                }
            }
        }
        return dp[A.length - 1];

    }
}