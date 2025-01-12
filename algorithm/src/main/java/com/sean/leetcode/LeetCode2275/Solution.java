package com.sean.leetcode.LeetCode2275;

/**
 * @Author xionghaiyang
 * @Date 2025-01-12 17:04
 * @Description https://leetcode.cn/problems/largest-combination-with-bitwise-and-greater-than-zero/
 * 2275. 按位与结果大于零的最长组合
 * 对数组 nums 执行 按位与 相当于对数组 nums 中的所有整数执行 按位与 。
 * 例如，对 nums = [1, 5, 3] 来说，按位与等于 1 & 5 & 3 = 1 。
 * 同样，对 nums = [7] 而言，按位与等于 7 。
 * 给你一个正整数数组 candidates 。
 * 计算 candidates 中的数字每种组合下 按位与 的结果。
 * 返回按位与结果大于 0 的 最长 组合的长度。
 * 1 <= candidates.length <= 10^5
 * 1 <= candidates[i] <= 10^7
 */
public class Solution {

    public int largestCombination(int[] candidates) {
        int res = 0;
        for (int i = 0; i < 24; i++) {
            res = Math.max(res, maxLen(candidates, i));
        }
        return res;
    }

    private int maxLen(int[] candidates, int k) {
        int res = 0;
        for (int num : candidates) {
            if ((num & (1 << k)) != 0) {
                res++;
            }
        }
        return res;
    }

}
