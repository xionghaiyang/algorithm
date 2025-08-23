package com.sean.leetcode.LeetCode1493;

/**
 * @Auther: xionghaiyang
 * @Date: 2023-05-30 14:42
 * @Description: https://leetcode.cn/problems/longest-subarray-of-1s-after-deleting-one-element
 * 1493. 删掉一个元素以后全为 1 的最长子数组
 * 给你一个二进制数组 nums ，你需要从中删掉一个元素。
 * 请你在删掉元素的结果数组中，返回最长的且只包含 1 的非空子数组的长度。
 * 如果不存在这样的子数组，请返回 0 。
 * 1 <= nums.length <= 10^5
 * nums[i] 要么是 0 要么是 1 。
 */
public class Solution {

    public int longestSubarray(int[] nums) {
        int n = nums.length;
        int left = -1, right = -1;
        //right移动到第一个为0的位置
        right++;
        while (right < n && nums[right] != 0) {
            right++;
        }
        int next = right;
        if (right < n) {
            //right移动到第二为0的位置
            right++;
            while (right < n && nums[right] != 0) {
                right++;
            }
        }
        int res = right - left - 2;
        while (right < n) {
            left = next;
            next = right;
            right++;
            while (right < n && nums[right] != 0) {
                right++;
            }
            res = Math.max(res, right - left - 2);
        }
        return res;
    }

}
