package com.sean.leetcode.LeetCode1014;

/**
 * @Auther: xionghaiyang
 * @Date: 2022-12-15 14:36
 * @Description: https://leetcode.cn/problems/best-sightseeing-pair/?envType=study-plan&id=dong-tai-gui-hua-ru-men&plan=dynamic-programming&plan_progress=yn1qp15
 * 1014. 最佳观光组合
 * 给你一个正整数数组 values，其中 values[i] 表示第 i 个观光景点的评分，并且两个景点 i 和 j 之间的 距离 为 j - i。
 * 一对景点（i < j）组成的观光组合的得分为 values[i] + values[j] + i - j ，也就是景点的评分之和 减去 它们两者之间的距离。
 * 返回一对观光景点能取得的最高分。
 */
public class Solution {

    public int maxScoreSightseeingPair(int[] values) {
        int n = values.length;
        int res = 0, max = values[0] + 0;
        for (int j = 1; j < n; j++) {
            res = Math.max(res, max + values[j] - j);
            max = Math.max(max, values[j] + j);
        }
        return res;
    }

}
