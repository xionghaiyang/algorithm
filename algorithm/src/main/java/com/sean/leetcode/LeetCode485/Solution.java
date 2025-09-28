package com.sean.leetcode.LeetCode485;

/**
 * @Author xionghaiyang
 * @Date 2025-09-28 17:13
 * @Description https://leetcode.cn/problems/max-consecutive-ones
 * 485. 最大连续 1 的个数
 * 给定一个二进制数组 nums ， 计算其中最大连续 1 的个数。
 * 1 <= nums.length <= 10^5
 * nums[i] 不是 0 就是 1.
 */
public class Solution {

    public int findMaxConsecutiveOnes(int[] nums) {
        int res = 0;
        int n = nums.length;
        for (int i = 0, cur = 0; i < n; i++) {
            if (nums[i] == 1) {
                cur++;
                if (cur > res) {
                    res = cur;
                }
            } else {
                cur = 0;
            }
        }
        return res;
    }

}
