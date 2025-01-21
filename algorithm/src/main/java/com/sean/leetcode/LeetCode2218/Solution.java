package com.sean.leetcode.LeetCode2218;

import java.util.Arrays;
import java.util.List;

/**
 * @Author xionghaiyang
 * @Date 2025-01-21 14:22
 * @Description https://leetcode.cn/problems/maximum-value-of-k-coins-from-piles/
 * 2218. 从栈中取出 K 个硬币的最大面值和
 * 一张桌子上总共有 n 个硬币 栈 。
 * 每个栈有 正整数 个带面值的硬币。
 * 每一次操作中，你可以从任意一个栈的 顶部 取出 1 个硬币，从栈中移除它，并放入你的钱包里。
 * 给你一个列表 piles ，其中 piles[i] 是一个整数数组，分别表示第 i 个栈里 从顶到底 的硬币面值。
 * 同时给你一个正整数 k ，请你返回在 恰好 进行 k 次操作的前提下，你钱包里硬币面值之和 最大为多少 。
 * n == piles.length
 * 1 <= n <= 1000
 * 1 <= piles[i][j] <= 10^5
 * 1 <= k <= sum(piles[i].length) <= 2000
 */
public class Solution {

    public int maxValueOfCoins(List<List<Integer>> piles, int k) {
        int[] f = new int[k + 1];
        Arrays.fill(f, -1);
        f[0] = 0;
        for (List<Integer> pile : piles) {
            for (int i = k; i > 0; i--) {
                int value = 0;
                for (int t = 1; t <= pile.size(); t++) {
                    value += pile.get(t - 1);
                    if (i >= t && f[i - t] != -1) {
                        f[i] = Math.max(f[i], f[i - t] + value);
                    }
                }
            }
        }
        return f[k];
    }

}
