package com.sean.leetcode.LeetCode3363;

import java.util.Arrays;

/**
 * @Author xionghaiyang
 * @Date 2025-08-07 06:25
 * @Description https://leetcode.cn/problems/find-the-maximum-number-of-fruits-collected
 * 3363. 最多可收集的水果数目
 * 有一个游戏，游戏由 n x n 个房间网格状排布组成。
 * 给你一个大小为 n x n 的二维整数数组 fruits ，其中 fruits[i][j] 表示房间 (i, j) 中的水果数目。
 * 有三个小朋友 一开始 分别从角落房间 (0, 0) ，(0, n - 1) 和 (n - 1, 0) 出发。
 * 每一位小朋友都会 恰好 移动 n - 1 次，并到达房间 (n - 1, n - 1) ：
 * 从 (0, 0) 出发的小朋友每次移动从房间 (i, j) 出发，可以到达 (i + 1, j + 1) ，(i + 1, j) 和 (i, j + 1) 房间之一（如果存在）。
 * 从 (0, n - 1) 出发的小朋友每次移动从房间 (i, j) 出发，可以到达房间 (i + 1, j - 1) ，(i + 1, j) 和 (i + 1, j + 1) 房间之一（如果存在）。
 * 从 (n - 1, 0) 出发的小朋友每次移动从房间 (i, j) 出发，可以到达房间 (i - 1, j + 1) ，(i, j + 1) 和 (i + 1, j + 1) 房间之一（如果存在）。
 * 当一个小朋友到达一个房间时，会把这个房间里所有的水果都收集起来。
 * 如果有两个或者更多小朋友进入同一个房间，只有一个小朋友能收集这个房间的水果。
 * 当小朋友离开一个房间时，这个房间里不会再有水果。
 * 请你返回三个小朋友总共 最多 可以收集多少个水果。
 * 2 <= n == fruits.length == fruits[i].length <= 1000
 * 0 <= fruits[i][j] <= 1000
 */
public class Solution {

    public int maxCollectedFruits(int[][] fruits) {
        int n = fruits.length;
        int[][] memo = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(memo[i], -1);
        }
        int res = 0;
        //(0,0)出发的小朋友
        for (int i = 0; i < n; i++) {
            res += fruits[i][i];
        }
        //从(0,n-1)出发的小朋友(倒着走)
        //从下往上走
        res += dfs(n - 2, n - 1, fruits, memo);
        //从(n-1,0)出发的小朋友(按主对角线反转，然后倒走着)
        //把下三角形中的数据填到上三角形中
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                fruits[j][i] = fruits[i][j];
            }
        }
        for (int i = 0; i < n; i++) {
            Arrays.fill(memo[i], -1);
        }
        res += dfs(n - 2, n - 1, fruits, memo);
        return res;
    }

    private int dfs(int i, int j, int[][] fruits, int[][] memo) {
        int n = fruits.length;
        if (j < n - 1 - i || j >= n) {
            return Integer.MIN_VALUE;
        }
        if (i == 0) {
            return fruits[i][j];
        }
        if (memo[i][j] != -1) {
            return memo[i][j];
        }
        int res = Math.max(Math.max(dfs(i - 1, j - 1, fruits, memo), dfs(i - 1, j, fruits, memo)), dfs(i - 1, j + 1, fruits, memo)) + fruits[i][j];
        return memo[i][j] = res;
    }

}
