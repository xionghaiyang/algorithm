package com.sean.leetcode.LeetCode576;

/**
 * @Auther: xionghaiyang
 * @Date: 2024-01-01 18:10
 * @Description: https://leetcode.cn/problems/out-of-boundary-paths/description/
 * 576. 出界的路径数
 * 给你一个大小为 m x n 的网格和一个球。
 * 球的起始坐标为 [startRow, startColumn] 。
 * 你可以将球移到在四个方向上相邻的单元格内（可以穿过网格边界到达网格之外）。
 * 你 最多 可以移动 maxMove 次球。
 * 给你五个整数 m、n、maxMove、startRow 以及 startColumn ，找出并返回可以将球移出边界的路径数量。
 * 因为答案可能非常大，返回对 10^9 + 7 取余 后的结果。
 */
public class Solution {

    public int findPaths(int m, int n, int maxMove, int startRow, int startColumn) {
        int res = 0;
        int mod = 1000000007;
        int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        //dp[i][j][k]表示球移动i次之后位于(j,k)的路径数量
        int[][][] dp = new int[maxMove + 1][m][n];
        dp[0][startRow][startColumn] = 1;
        for (int i = 0; i < maxMove; i++) {
            for (int j = 0; j < m; j++) {
                for (int k = 0; k < n; k++) {
                    int count = dp[i][j][k];
                    if (count > 0) {
                        for (int[] dir : dirs) {
                            int nj = j + dir[0];
                            int nk = k + dir[1];
                            if (nj >= 0 && nj < m && nk >= 0 && nk < n) {
                                dp[i + 1][nj][nk] = (dp[i + 1][nj][nk] + count) % mod;
                            } else {
                                res = (res + count) % mod;
                            }
                        }
                    }
                }
            }
        }
        return res;
    }

}
