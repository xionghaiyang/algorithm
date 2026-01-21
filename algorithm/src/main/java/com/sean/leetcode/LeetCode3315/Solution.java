package com.sean.leetcode.LeetCode3315;

import java.util.List;

/**
 * @Author xionghaiyang
 * @Date 2026-01-21 08:23
 * @Description https://leetcode.cn/problems/construct-the-minimum-bitwise-array-ii
 * 3315. 构造最小位运算数组 II
 * 给你一个长度为 n 的质数数组 nums 。
 * 你的任务是返回一个长度为 n 的数组 ans ，对于每个下标 i ，以下 条件 均成立：
 * ans[i] OR (ans[i] + 1) == nums[i]
 * 除此以外，你需要 最小化 结果数组里每一个 ans[i] 。
 * 如果没法找到符合 条件 的 ans[i] ，那么 ans[i] = -1 。
 * 质数 指的是一个大于 1 的自然数，且它只有 1 和自己两个因数。
 * 1 <= nums.length <= 100
 * 2 <= nums[i] <= 10^9
 * nums[i] 是一个质数。
 */
public class Solution {

    public int[] minBitwiseArray(List<Integer> nums) {
        int n = nums.size();
        int[] res = new int[n];
        for (int i = 0; i < n; i++) {
            int x = nums.get(i);
            if (x == 2) {
                res[i] = -1;
            } else {
                int lowbit = (x + 1) & (-x);
                res[i] = x ^ (lowbit >> 1);
            }
        }
        return res;
    }

}
