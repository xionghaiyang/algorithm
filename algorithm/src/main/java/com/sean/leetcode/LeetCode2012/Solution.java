package com.sean.leetcode.LeetCode2012;

/**
 * @Author xionghaiyang
 * @Date 2025-03-11 08:29
 * @Description https://leetcode.cn/problems/sum-of-beauty-in-the-array
 * 2012. 数组美丽值求和
 * 给你一个下标从 0 开始的整数数组 nums 。
 * 对于每个下标 i（1 <= i <= nums.length - 2），nums[i] 的 美丽值 等于：
 * 2，对于所有 0 <= j < i 且 i < k <= nums.length - 1 ，满足 nums[j] < nums[i] < nums[k]
 * 1，如果满足 nums[i - 1] < nums[i] < nums[i + 1] ，且不满足前面的条件
 * 0，如果上述条件全部不满足
 * 返回符合 1 <= i <= nums.length - 2 的所有 nums[i] 的 美丽值的总和 。
 * 3 <= nums.length <= 10^5
 * 1 <= nums[i] <= 10^5
 */
public class Solution {

    public int sumOfBeauties(int[] nums) {
        int n = nums.length;
        //后缀最小值
        int[] sufMin = new int[n];
        sufMin[n - 1] = nums[n - 1];
        for (int i = n - 2; i > 1; i--) {
            sufMin[i] = Math.min(sufMin[i + 1], nums[i]);
        }
        int res = 0;
        //前缀最大值
        int preMax = nums[0];
        for (int i = 1; i < n - 1; i++) {
            int x = nums[i];
            if (preMax < x && x < sufMin[i + 1]) {
                res += 2;
            } else if (nums[i - 1] < x && x < nums[i + 1]) {
                res++;
            }
            preMax = Math.max(preMax, x);
        }
        return res;
    }

}
