package com.sean.leetcode.LeetCode2598;

/**
 * @Author xionghaiyang
 * @Date 2025-10-16 12:32
 * @Description https://leetcode.cn/problems/smallest-missing-non-negative-integer-after-operations
 * 2598. 执行操作后的最大 MEX
 * 给你一个下标从 0 开始的整数数组 nums 和一个整数 value 。
 * 在一步操作中，你可以对 nums 中的任一元素加上或减去 value 。
 * 例如，如果 nums = [1,2,3] 且 value = 2 ，你可以选择 nums[0] 减去 value ，得到 nums = [-1,2,3] 。
 * 数组的 MEX (minimum excluded) 是指其中数组中缺失的最小非负整数。
 * 例如，[-1,2,3] 的 MEX 是 0 ，而 [1,0,3] 的 MEX 是 2 。
 * 返回在执行上述操作 任意次 后，nums 的最大 MEX 。
 * 1 <= nums.length, value <= 10^5
 * -10^9 <= nums[i] <= 10^9
 */
public class Solution {

    public int findSmallestInteger(int[] nums, int value) {
        int[] cnt = new int[value];
        for (int num : nums) {
            cnt[(num % value + value) % value]++;
        }
        int res = 0;
        while (cnt[res % value]-- > 0) {
            res++;
        }
        return res;
    }

}
