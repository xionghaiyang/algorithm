package com.sean.leetcode.LeetCode2861;

import java.util.Collections;
import java.util.List;

/**
 * @Auther: xionghaiyang
 * @Date: 2024-01-27 10:39
 * @Description: https://leetcode.cn/problems/maximum-number-of-alloys/
 * 2861. 最大合金数
 * 假设你是一家合金制造公司的老板，你的公司使用多种金属来制造合金。
 * 现在共有 n 种不同类型的金属可以使用，并且你可以使用 k 台机器来制造合金。
 * 每台机器都需要特定数量的每种金属来创建合金。
 * 对于第 i 台机器而言，创建合金需要 composition[i][j] 份 j 类型金属。
 * 最初，你拥有 stock[i] 份 i 类型金属，而每购入一份 i 类型金属需要花费 cost[i] 的金钱。
 * 给你整数 n、k、budget，下标从 1 开始的二维数组 composition，两个下标从 1 开始的数组 stock 和 cost，
 * 请你在预算不超过 budget 金钱的前提下，最大化 公司制造合金的数量。
 * 所有合金都需要由同一台机器制造。
 * 返回公司可以制造的最大合金数。
 */
public class Solution {

    public int maxNumberOfAlloys(int n, int k, int budget, List<List<Integer>> composition, List<Integer> stock, List<Integer> cost) {
        int res = 0;
        int mx = Collections.min(stock) + budget;
        for (List<Integer> comp : composition) {
            int left = res, right = mx + 1;
            while (left + 1 < right) {
                int mid = left + (right - left) / 2;
                boolean ok = true;
                long money = 0;
                for (int i = 0; i < n; i++) {
                    if (stock.get(i) < (long) comp.get(i) * mid) {
                        money += ((long) comp.get(i) * mid - stock.get(i)) * cost.get(i);
                        if (money > budget) {
                            ok = false;
                            break;
                        }
                    }
                }
                if (ok) {
                    left = mid;
                } else {
                    right = mid;
                }
            }
            res = left;
        }
        return res;
    }

}
