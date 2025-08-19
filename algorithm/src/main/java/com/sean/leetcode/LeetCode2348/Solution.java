package com.sean.leetcode.LeetCode2348;

/**
 * @Author xionghaiyang
 * @Date 2025-08-19 10:18
 * @Description https://leetcode.cn/problems/number-of-zero-filled-subarrays
 * 2348. 全 0 子数组的数目
 * 给你一个整数数组 nums ，返回全部为 0 的 子数组 数目。
 * 子数组 是一个数组中一段连续非空元素组成的序列。
 * 1 <= nums.length <= 10^5
 * -10^9 <= nums[i] <= 10^9
 */
public class Solution {

    public long zeroFilledSubarray(int[] nums) {
        long res = 0;
        int cnt = 0;
        for (int num : nums) {
            if (num == 0) {
                cnt++;
                res += cnt;
            } else {
                cnt = 0;
            }
        }
        return res;
    }

}
