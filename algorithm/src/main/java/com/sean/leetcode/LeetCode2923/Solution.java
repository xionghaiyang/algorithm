package com.sean.leetcode.LeetCode2923;

/**
 * @Auther: xionghaiyang
 * @Date: 2024-04-12 13:59
 * @Description: https://leetcode.cn/problems/find-champion-i/
 * 2923. 找到冠军 I
 * 一场比赛中共有 n 支队伍，按从 0 到  n - 1 编号。
 * 给你一个下标从 0 开始、大小为 n * n 的二维布尔矩阵 grid 。
 * 对于满足 0 <= i, j <= n - 1 且 i != j 的所有 i, j ：如果 grid[i][j] == 1，那么 i 队比 j 队 强 ；否则，j 队比 i 队 强 。
 * 在这场比赛中，如果不存在某支强于 a 队的队伍，则认为 a 队将会是 冠军 。
 * 返回这场比赛中将会成为冠军的队伍。
 */
public class Solution {

    public int findChampion(int[][] grid) {
        int n = grid.length;
        for (int i = 0; i < n; i++) {
            int[] line = grid[i];
            int sum = 0;
            for (int num : line) {
                sum += num;
            }
            if (sum == n - 1) {
                return i;
            }
        }
        return -1;
    }

}
