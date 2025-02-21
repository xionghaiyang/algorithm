package com.sean.leetcode.LeetCode2209;

/**
 * @Author xionghaiyang
 * @Date 2025-02-21 11:38
 * @Description https://leetcode.cn/problems/minimum-white-tiles-after-covering-with-carpets/
 * 2209. 用地毯覆盖后的最少白色砖块
 * 给你一个下标从 0 开始的 二进制 字符串 floor ，它表示地板上砖块的颜色。
 * floor[i] = '0' 表示地板上第 i 块砖块的颜色是 黑色 。
 * floor[i] = '1' 表示地板上第 i 块砖块的颜色是 白色 。
 * 同时给你 numCarpets 和 carpetLen 。你有 numCarpets 条 黑色 的地毯，每一条 黑色 的地毯长度都为 carpetLen 块砖块。
 * 请你使用这些地毯去覆盖砖块，使得未被覆盖的剩余 白色 砖块的数目 最小 。
 * 地毯相互之间可以覆盖。
 * 请你返回没被覆盖的白色砖块的 最少 数目。
 * 1 <= carpetLen <= floor.length <= 1000
 * floor[i] 要么是 '0' ，要么是 '1' 。
 * 1 <= numCarpets <= 1000
 */
public class Solution {

    static final int INF = 0x3f3f3f3f;

    public int minimumWhiteTiles(String floor, int numCarpets, int carpetLen) {
        int n = floor.length();
        int[][] dp = new int[n + 1][numCarpets + 1];
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= numCarpets; j++) {
                dp[i][j] = INF;
            }
        }
        for (int j = 0; j <= numCarpets; j++) {
            dp[0][j] = 0;
        }
        for (int i = 1; i <= n; i++) {
            dp[i][0] = dp[i - 1][0] + (floor.charAt(i - 1) == '1' ? 1 : 0);
        }
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= numCarpets; j++) {
                dp[i][j] = dp[i - 1][j] + (floor.charAt(i - 1) == '1' ? 1 : 0);
                dp[i][j] = Math.min(dp[i][j], dp[Math.max(0, i - carpetLen)][j - 1]);
            }
        }
        return dp[n][numCarpets];
    }

}
