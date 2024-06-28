package com.sean.leetcode.LeetCode1493;

/**
 * @Auther: xionghaiyang
 * @Date: 2023-05-30 14:42
 * @Description: https://leetcode.cn/problems/longest-subarray-of-1s-after-deleting-one-element/?envType=study-plan-v2&envId=leetcode-75
 * 1493. 删掉一个元素以后全为 1 的最长子数组
 * 给你一个二进制数组 nums ，你需要从中删掉一个元素。
 * 请你在删掉元素的结果数组中，返回最长的且只包含 1 的非空子数组的长度。
 * 如果不存在这样的子数组，请返回 0 。
 */
public class Solution {

    //最多只包含一个 0 的连续区间的最大长度
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
            //right移动到第二个为0的位置
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
