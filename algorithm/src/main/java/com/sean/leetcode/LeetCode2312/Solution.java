package com.sean.leetcode.LeetCode2312;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: xionghaiyang
 * @Date: 2024-03-15 12:27
 * @Description: https://leetcode.cn/problems/selling-pieces-of-wood/
 * 2312. 卖木头块
 * 给你两个整数 m 和 n ，分别表示一块矩形木块的高和宽。
 * 同时给你一个二维整数数组 prices ，其中 prices[i] = [hi, wi, pricei] 表示你可以以 pricei 元的价格卖一块高为 hi 宽为 wi 的矩形木块。
 * 每一次操作中，你必须按下述方式之一执行切割操作，以得到两块更小的矩形木块：
 * 沿垂直方向按高度 完全 切割木块，或
 * 沿水平方向按宽度 完全 切割木块
 * 在将一块木块切成若干小木块后，你可以根据 prices 卖木块。
 * 你可以卖多块同样尺寸的木块。
 * 你不需要将所有小木块都卖出去。
 * 你 不能 旋转切好后木块的高和宽。
 * 请你返回切割一块大小为 m x n 的木块后，能得到的 最多 钱数。
 * 注意你可以切割木块任意次。
 */
public class Solution {

    Map<Long, Integer> map;
    long[][] memo;

    public long sellingWood(int m, int n, int[][] prices) {
        map = new HashMap<>();
        for (int[] price : prices) {
            map.put(pairHash(price[0], price[1]), price[2]);
        }
        memo = new long[m + 1][n + 1];
        for (long[] row : memo) {
            Arrays.fill(row, -1);
        }
        return dfs(m, n);
    }

    private long dfs(int x, int y) {
        if (memo[x][y] != -1) {
            return memo[x][y];
        }
        long key = pairHash(x, y);
        long res = map.getOrDefault(key, 0);
        if (x > 1) {
            for (int i = 1; i < x; i++) {
                res = Math.max(res, dfs(i, y) + dfs(x - i, y));
            }
        }
        if (y > 1) {
            for (int j = 1; j < y; j++) {
                res = Math.max(res, dfs(x, j) + dfs(x, y - j));
            }
        }
        memo[x][y] = res;
        return res;
    }

    private long pairHash(int x, int y) {
        return (long) x << 16 ^ y;
    }

}
