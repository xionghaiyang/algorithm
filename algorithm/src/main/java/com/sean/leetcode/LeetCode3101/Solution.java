package com.sean.leetcode.LeetCode3101;

/**
 * @Author xionghaiyang
 * @Date 2024-07-06 07:02
 * @Description https://leetcode.cn/problems/count-alternating-subarrays/
 * 3101. 交替子数组计数
 * 给你一个二进制数组 nums 。
 * 如果一个 子数组中 不存在 两个 相邻 元素的值 相同 的情况，我们称这样的子数组为 交替子数组 。
 * 返回数组 nums 中交替子数组的数量。
 */
public class Solution {

    public long countAlternatingSubarrays(int[] nums) {
        int n = nums.length;
        int left = 0, right = 0;
        long res = 0;
        while (right < n) {
            right++;
            while (right < n && nums[right - 1] != nums[right]) {
                right++;
            }
            res += (long) (right - left) * (right - left + 1) / 2;
            left = right;
        }
        return res;
    }

}
