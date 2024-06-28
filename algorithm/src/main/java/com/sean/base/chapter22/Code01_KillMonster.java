package com.sean.base.chapter22;

/**
 * @Auther: xionghaiyang
 * @Date: 2022-11-09 19:56
 * @Description: TODO
 */
public class Code01_KillMonster {

    public double right(int N, int M, int K) {
        if (N < 1 || M < 1 || K < 1) {
            return 0;
        }
        double all = Math.pow(M + 1, K);
        long kill = process(K, M, N);
        return (double) kill / all;
    }

    //怪兽还剩hp点血
    //每次的伤害在[0~M]范围上
    //还有times次可以砍
    //返回砍死的情况数
    private long process(int times, int M, int hp) {
        if (times == 0) {
            return hp <= 0 ? 1 : 0;
        }
        if (hp <= 0) {
            return (long) Math.pow(M + 1, times);
        }
        long ways = 0;
        for (int i = 0; i <= M; i++) {
            ways += process(times - 1, M, hp - i);
        }
        return ways;
    }

    public double dp1(int N, int M, int K) {
        if (N < 1 || M < 1 || K < 1) {
            return 0;
        }
        double all = Math.pow(M + 1, K);
        long[][] dp = new long[K + 1][N + 1];
        dp[0][0] = 1;
        for (int times = 1; times <= K; times++) {
            dp[times][0] = (long) Math.pow(M + 1, times);
            for (int hp = 1; hp <= N; hp++) {
                long ways = 0;
                for (int i = 0; i <= M; i++) {
                    if (hp - i >= 0) {
                        ways += dp[times - 1][hp - i];
                    } else {
                        ways += (long) Math.pow(M + 1, times - 1);
                    }
                }
                dp[times][hp] = ways;
            }
        }
        long kill = dp[K][N];
        return (double) kill / all;
    }

    public double dp2(int N, int M, int K) {
        if (N < 1 || M < 1 || K < 1) {
            return 0;
        }
        double all = Math.pow(M + 1, K);
        long[][] dp = new long[K + 1][N + 1];
        dp[0][0] = 1;
        for (int times = 1; times <= K; times++) {
            dp[times][0] = (long) Math.pow(M + 1, times);
            for (int hp = 1; hp <= N; hp++) {
                dp[times][hp] = dp[times][hp - 1] + dp[times - 1][hp];
                if (hp - 1 - M >= 0) {
                    dp[times][hp] -= dp[times - 1][hp - 1 - M];
                } else {
                    dp[times][hp] -= (long) Math.pow(M + 1, times - 1);
                }
            }
        }
        long kill = dp[K][N];
        return (double) kill / all;
    }

    public static void main(String[] args) {
        Code01_KillMonster solution = new Code01_KillMonster();
        int NMax = 10;
        int MMax = 10;
        int KMax = 10;
        int testTime = 200;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int N = (int) (Math.random() * NMax);
            int M = (int) (Math.random() * MMax);
            int K = (int) (Math.random() * KMax);
            double res1 = solution.right(N, M, K);
            double res2 = solution.dp1(N, M, K);
            double res3 = solution.dp2(N, M, K);
            if (res1 != res2 || res1 != res3) {
                System.out.println("Oops!");
                break;
            }
        }
        System.out.println("测试结束");
    }

}
