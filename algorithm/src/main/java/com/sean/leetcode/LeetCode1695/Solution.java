package com.sean.leetcode.LeetCode1695;

/**
 * @Author xionghaiyang
 * @Date 2025-07-22 06:00
 * @Description https://leetcode.cn/problems/maximum-erasure-value
 * 1695. 删除子数组的最大得分
 * 给你一个正整数数组 nums ，请你从中删除一个含有 若干不同元素 的子数组。
 * 删除子数组的 得分 就是子数组各元素之 和 。
 * 返回 只删除一个 子数组可获得的 最大得分 。
 * 如果数组 b 是数组 a 的一个连续子序列，即如果它等于 a[l],a[l+1],...,a[r] ，那么它就是 a 的一个子数组。
 * 1 <= nums.length <= 10^5
 * 1 <= nums[i] <= 10^4
 */
public class Solution {

    public int maximumUniqueSubarray(int[] nums) {
        int max = 0;
        for (int num : nums) {
            max = Math.max(max, num);
        }
        boolean[] has = new boolean[max + 1];
        int res = 0, sum = 0, left = 0;
        for (int num : nums) {
            while (has[num]) {
                has[nums[left]] = false;
                sum -= nums[left];
                left++;
            }
            has[num] = true;
            sum += num;
            res = Math.max(res, sum);
        }
        return res;
    }

}
