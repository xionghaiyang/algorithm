package com.sean.leetcode.LeetCode2209;

/**
 * @Author xionghaiyang
 * @Date 2025-02-21 11:38
 * @Description https://leetcode.cn/problems/minimum-white-tiles-after-covering-with-carpets
 * 2209. 用地毯覆盖后的最少白色砖块
 * 给你一个下标从 0 开始的 二进制 字符串 floor ，它表示地板上砖块的颜色。
 * floor[i] = '0' 表示地板上第 i 块砖块的颜色是 黑色 。
 * floor[i] = '1' 表示地板上第 i 块砖块的颜色是 白色 。
 * 同时给你 numCarpets 和 carpetLen 。
 * 你有 numCarpets 条 黑色 的地毯，每一条 黑色 的地毯长度都为 carpetLen 块砖块。
 * 请你使用这些地毯去覆盖砖块，使得未被覆盖的剩余 白色 砖块的数目 最小 。
 * 地毯相互之间可以覆盖。
 * 请你返回没被覆盖的白色砖块的 最少 数目。
 * 1 <= carpetLen <= floor.length <= 1000
 * floor[i] 要么是 '0' ，要么是 '1' 。
 * 1 <= numCarpets <= 1000
 */
public class Solution {

    public int minimumWhiteTiles(String floor, int numCarpets, int carpetLen) {
        char[] str = floor.toCharArray();
        int n = str.length;
        //dp[i][j]:用i条地毯覆盖下标在[0,j]中的砖，没被覆盖的白色砖块的最少数目
        int[][] dp = new int[numCarpets + 1][n];
        dp[0][0] = str[0] - '0';
        for (int j = 1; j < n; j++) {
            dp[0][j] = dp[0][j - 1] + (str[j] - '0');
        }
        for (int i = 1; i <= numCarpets; i++) {
            for (int j = carpetLen * i; j < n; j++) {
                dp[i][j] = Math.min(dp[i][j - 1] + (str[j] - '0'), dp[i - 1][j - carpetLen]);
            }
        }
        return dp[numCarpets][n - 1];
    }

    public int minimumWhiteTiles1(String floor, int numCarpets, int carpetLen) {
        int n = floor.length();
        if (carpetLen * numCarpets >= n) {
            return 0;
        }
        char[] str = floor.toCharArray();
        int[] f = new int[n];
        f[0] = str[0] - '0';
        for (int j = 1; j < n; j++) {
            f[j] = f[j - 1] + (str[j] - '0');
        }
        for (int i = 1; i <= numCarpets; i++) {
            int[] nf = new int[n];
            for (int j = carpetLen * i; j < n; j++) {
                nf[j] = Math.min(nf[j - 1] + (str[j] - '0'), f[j - carpetLen]);
            }
            f = nf;
        }
        return f[n - 1];
    }

}
