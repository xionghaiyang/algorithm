package com.sean.leetcode.LeetCode2132;

/**
 * @Auther: xionghaiyang
 * @Date: 2023-12-14 17:06
 * @Description: https://leetcode.cn/problems/stamping-the-grid/
 * 2132. 用邮票贴满网格图
 * 给你一个 m x n 的二进制矩阵 grid ，每个格子要么为 0 （空）要么为 1 （被占据）。
 * 给你邮票的尺寸为 stampHeight x stampWidth 。我们想将邮票贴进二进制矩阵中，且满足以下 限制 和 要求 ：
 * 覆盖所有 空 格子。
 * 不覆盖任何 被占据 的格子。
 * 我们可以放入任意数目的邮票。
 * 邮票可以相互有 重叠 部分。
 * 邮票不允许 旋转 。
 * 邮票必须完全在矩阵 内 。
 * 如果在满足上述要求的前提下，可以放入邮票，请返回 true ，否则返回 false 。
 */
public class Solution {

    public boolean possibleToStamp(int[][] grid, int stampHeight, int stampWidth) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] sum = new int[m + 1][n + 1];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                sum[i + 1][j + 1] = sum[i + 1][j] + sum[i][j + 1] - sum[i][j] + grid[i][j];
            }
        }
        int[][] diff = new int[m + 2][n + 2];
        for (int i = stampHeight; i <= m; i++) {
            for (int j = stampWidth; j <= n; j++) {
                int x = i - stampHeight + 1;
                int y = j - stampWidth + 1;
                if (sum[i][j] - sum[i][y - 1] - sum[x - 1][j] + sum[x - 1][y - 1] == 0) {
                    diff[x][y]++;
                    diff[x][j + 1]--;
                    diff[i + 1][y]--;
                    diff[i + 1][j + 1]++;
                }
            }
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                diff[i + 1][j + 1] += diff[i + 1][j] + diff[i][j + 1] - diff[i][j];
                if (grid[i][j] == 0 && diff[i + 1][j + 1] == 0) {
                    return false;
                }
            }
        }
        return true;
    }

}
