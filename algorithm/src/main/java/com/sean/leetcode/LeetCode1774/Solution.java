package com.sean.leetcode.LeetCode1774;

import java.util.Arrays;

/**
 * @Author: xionghaiyang
 * @Date: 2022-12-04 20:36
 * @Description: https://leetcode.cn/problems/closest-dessert-cost
 * 1774. 最接近目标价格的甜点成本
 * 你打算做甜点，现在需要购买配料。
 * 目前共有 n 种冰激凌基料和 m 种配料可供选购。
 * 而制作甜点需要遵循以下几条规则：
 * 必须选择 一种 冰激凌基料。
 * 可以添加 一种或多种 配料，也可以不添加任何配料。
 * 每种类型的配料 最多两份 。
 * 给你以下三个输入：
 * baseCosts ，一个长度为 n 的整数数组，其中每个 baseCosts[i] 表示第 i 种冰激凌基料的价格。
 * toppingCosts，一个长度为 m 的整数数组，其中每个 toppingCosts[i] 表示 一份 第 i 种冰激凌配料的价格。
 * target ，一个整数，表示你制作甜点的目标价格。
 * 你希望自己做的甜点总成本尽可能接近目标价格 target 。
 * 返回最接近 target 的甜点成本。
 * 如果有多种方案，返回 成本相对较低 的一种。
 * n == baseCosts.length
 * m == toppingCosts.length
 * 1 <= n, m <= 10
 * 1 <= baseCosts[i], toppingCosts[i] <= 10^4
 * 1 <= target <= 10^4
 */
public class Solution {

    public int closestCost(int[] baseCosts, int[] toppingCosts, int target) {
        int x = Arrays.stream(baseCosts).min().getAsInt();
        if (x >= target) {
            return x;
        }
        boolean[] can = new boolean[target + 1];
        int res = 2 * target - x;
        for (int cost : baseCosts) {
            if (cost <= target) {
                can[cost] = true;
            } else {
                res = Math.min(res, cost);
            }
        }
        for (int cost : toppingCosts) {
            for (int k = 0; k < 2; k++) {
                for (int i = target; i > 0; i--) {
                    if (can[i] && i + cost > target) {
                        res = Math.min(res, i + cost);
                    }
                    if (i - cost > 0) {
                        can[i] |= can[i - cost];
                    }
                }
            }
        }
        for (int i = 0; i <= res - target; i++) {
            if (can[target - i]) {
                return target - i;
            }
        }
        return res;
    }

}
