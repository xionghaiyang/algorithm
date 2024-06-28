package com.sean.leetcode.LeetCode790;

/**
 * @Auther: xionghaiyang
 * @Date: 2022-11-12 22:45
 * @Description: https://leetcode.cn/problems/domino-and-tromino-tiling/
 * 790. 多米诺和托米诺平铺
 * 有两种形状的瓷砖：一种是 2 x 1 的多米诺形，另一种是形如 "L" 的托米诺形。两种形状都可以旋转。
 * 给定整数 n ，返回可以平铺 2 x n 的面板的方法的数量。
 * 返回对 109 + 7 取模 的值。
 * 平铺指的是每个正方形都必须有瓷砖覆盖。
 * 两个平铺不同，当且仅当面板上有四个方向上的相邻单元中的两个，使得恰好有一个平铺有一个瓷砖占据两个正方形。
 */
public class Solution {

    int mod = 1000000007;

    public int numTilings1(int n) {
        return process(n, 3);
    }

    //平铺到第i列时，各个状态 s对应的平铺方法数量
    //一个正方形都没有被覆盖，记为状态 0
    //只有上方的正方形被覆盖，记为状态 1
    //只有下方的正方形被覆盖，记为状态 2
    //上下两个正方形都被覆盖，记为状态 3
    private int process(int i, int status) {
        if (i == 0) {
            return status == 3 ? 1 : 0;
        }
        if (status == 0) {
            return process(i - 1, 3);
        } else if (status == 1) {
            return (process(i - 1, 0) + process(i - 1, 2)) % mod;
        } else if (status == 2) {
            return (process(i - 1, 0) + process(i - 1, 1)) % mod;
        } else {
            return (((process(i - 1, 0) + process(i - 1, 1)) % mod + process(i - 1, 2)) % mod + process(i - 1, 3)) % mod;
        }
    }

    public int numTilings(int n) {
        int mod = 1000000007;
        int[][] dp = new int[n + 1][4];
        dp[0][3] = 1;
        for (int i = 1; i <= n; i++) {
            dp[i][0] = dp[i - 1][3];
            dp[i][1] = (dp[i - 1][0] + dp[i - 1][2]) % mod;
            dp[i][2] = (dp[i - 1][0] + dp[i - 1][1]) % mod;
            dp[i][3] = (((dp[i - 1][0] + dp[i - 1][1]) % mod + dp[i - 1][2]) % mod + dp[i - 1][3]) % mod;
        }
        return dp[n][3];
    }

}
