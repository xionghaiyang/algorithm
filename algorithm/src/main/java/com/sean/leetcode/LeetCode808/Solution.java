package com.sean.leetcode.LeetCode808;

/**
 * @Auther: xionghaiyang
 * @Date: 2022-11-21 09:23
 * @Description: https://leetcode.cn/problems/soup-servings
 * 808. 分汤
 * 有 A 和 B 两种类型 的汤。一开始每种类型的汤有 n 毫升。有四种分配操作：
 * 提供 100ml 的 汤A 和 0ml 的 汤B 。
 * 提供 75ml 的 汤A 和 25ml 的 汤B 。
 * 提供 50ml 的 汤A 和 50ml 的 汤B 。
 * 提供 25ml 的 汤A 和 75ml 的 汤B 。
 * 当我们把汤分配给某人之后，汤就没有了。
 * 每个回合，我们将从四种概率同为 0.25 的操作中进行分配选择。
 * 如果汤的剩余量不足以完成某次操作，我们将尽可能分配。
 * 当两种类型的汤都分配完时，停止操作。
 * 注意 不存在先分配 100 ml 汤B 的操作。
 * 需要返回的值： 汤A 先分配完的概率 +  汤A和汤B 同时分配完的概率 / 2。
 * 返回值在正确答案 10^-5 的范围内将被认为是正确的。
 * 0 <= n <= 10^9
 */
public class Solution {

    public double soupServings(int n) {
        n = (int) Math.ceil((double) n / 25);
        if (n >= 179) {
            return 1.0;
        }
        double[][] memo = new double[n + 1][n + 1];
        return dfs(n, n, memo);
    }

    private double dfs(int i, int j, double[][] memo) {
        if (i <= 0 && j <= 0) {
            return 0.5;
        } else if (i <= 0) {
            return 1.0;
        } else if (j <= 0) {
            return 0;
        }
        if (memo[i][j] == 0) {
            memo[i][j] = 0.25 * (dfs(i - 4, j, memo) + dfs(i - 3, j - 1, memo) + dfs(i - 2, j - 2, memo) + dfs(i - 1, j - 3, memo));
        }
        return memo[i][j];
    }

}
