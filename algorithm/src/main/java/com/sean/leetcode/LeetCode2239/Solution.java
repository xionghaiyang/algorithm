package com.sean.leetcode.LeetCode2239;

/**
 * @Author xionghaiyang
 * @Date 2025-01-20 17:54
 * @Description https://leetcode.cn/problems/find-closest-number-to-zero/
 * 2239. 找到最接近 0 的数字
 * 给你一个长度为 n 的整数数组 nums ，请你返回 nums 中最 接近 0 的数字。
 * 如果有多个答案，请你返回它们中的 最大值 。
 * 1 <= n <= 1000
 * -105 <= nums[i] <= 10^5
 */
public class Solution {

    public int findClosestNumber(int[] nums) {
        int n = nums.length;
        int res = nums[0];
        int dis = Math.abs(res);
        for (int i = 1; i < n; i++) {
            if (Math.abs(nums[i]) < dis) {
                res = nums[i];
                dis = Math.abs(nums[i]);
            } else if (Math.abs(nums[i]) == dis && nums[i] > res) {
                res = nums[i];
            }
        }
        return res;
    }

}
