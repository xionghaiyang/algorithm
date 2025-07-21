package com.sean.leetcode.LeetCode3623;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author xionghaiyang
 * @Date 2025-07-21 17:55
 * @Description https://leetcode.cn/problems/count-number-of-trapezoids-i
 * 3623. 统计梯形的数目 I
 * 给你一个二维整数数组 points，其中 points[i] = [xi, yi] 表示第 i 个点在笛卡尔平面上的坐标。
 * 水平梯形 是一种凸四边形，具有 至少一对 水平边（即平行于 x 轴的边）。
 * 两条直线平行当且仅当它们的斜率相同。
 * 返回可以从 points 中任意选择四个不同点组成的 水平梯形 数量。
 * 由于答案可能非常大，请返回结果对 10^9 + 7 取余数后的值。
 * 4 <= points.length <= 10^5
 * –10^8 <= xi, yi <= 10^8
 * 所有点两两不同。
 */
public class Solution {

    private static final int MOD = 1_000_000_007;

    public int countTrapezoids(int[][] points) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int[] point : points) {
            map.compute(point[1], (k, v) -> v == null ? 1 : v + 1);
        }
        long res = 0, preSum = 0;
        for (int cnt : map.values()) {
            long k = (long) cnt * (cnt - 1) / 2 % MOD;
            res = (res + preSum * k % MOD) % MOD;
            preSum = (preSum + k) % MOD;
        }
        return (int) res;
    }

}
