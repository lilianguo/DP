class JumpGame {
    public boolean canJump(int[] A) {
        // write your code here
        // dp[i] 表示能否到达第i个位置
        // 什么时候能到达i, 之前只要有一个位置j 满足 j 能到达 && j 到 i的步数 <= j位置最多能跳的步数
        // O(n^2)
        int n = A.length;
        boolean[] dp = new boolean[A.length];
        dp[0] = true;
        for (int i = 1; i < n; i++) {
            dp[i] = false;
            for (int j = 0; j < i; j++) {
                if (dp[j] && A[j] >= (i - j)) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[A.length - 1] == true;
    }
}