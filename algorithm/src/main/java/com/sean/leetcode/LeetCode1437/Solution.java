package com.sean.leetcode.LeetCode1437;

/**
 * @Auther: xionghaiyang
 * @Date: 2023-12-17 13:11
 * @Description: https://leetcode.cn/problems/check-if-all-1s-are-at-least-length-k-places-away/description/
 * 1437. 是否所有 1 都至少相隔 k 个元素
 * 给你一个由若干 0 和 1 组成的数组 nums 以及整数 k。如果所有 1 都至少相隔 k 个元素，则返回 True ；否则，返回 False 。
 */
public class Solution {

    public boolean kLengthApart(int[] nums, int k) {
        int n = nums.length;
        int prev = -1;
        for (int i = 0; i < n; i++) {
            if (nums[i] == 1) {
                if (prev != -1 && i - prev - 1 < k) {
                    return false;
                }
                prev = i;
            }
        }
        return true;
    }

}
