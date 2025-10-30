package com.sean.leetcode.LeetCode1526;

/**
 * @Author xionghaiyang
 * @Date 2025-10-30 11:20
 * @Description https://leetcode.cn/problems/minimum-number-of-increments-on-subarrays-to-form-a-target-array
 * 1526. 形成目标数组的子数组最少增加次数
 * 给你一个整数数组 target 和一个数组 initial ，initial 数组与 target  数组有同样的维度，且一开始全部为 0 。
 * 请你返回从 initial 得到  target 的最少操作次数，每次操作需遵循以下规则：
 * 在 initial 中选择 任意 子数组，并将子数组中每个元素增加 1 。
 * 答案保证在 32 位有符号整数以内。
 * 1 <= target.length <= 10^5
 * 1 <= target[i] <= 10^5
 */
public class Solution {

    public int minNumberOperations(int[] target) {
        int res = target[0];
        for (int i = 1; i < target.length; i++) {
            res += Math.max(target[i] - target[i - 1], 0);
        }
        return res;
    }

}
