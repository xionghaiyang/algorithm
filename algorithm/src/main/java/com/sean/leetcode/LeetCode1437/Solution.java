package com.sean.leetcode.LeetCode1437;

/**
 * @Auther: xionghaiyang
 * @Date: 2023-12-17 13:11
 * @Description: https://leetcode.cn/problems/check-if-all-1s-are-at-least-length-k-places-away
 * 1437. 是否所有 1 都至少相隔 k 个元素
 * 给你一个由若干 0 和 1 组成的数组 nums 以及整数 k。
 * 如果所有 1 都至少相隔 k 个元素，则返回 True ；否则，返回 False 。
 * 1 <= nums.length <= 10^5
 * 0 <= k <= nums.length
 * nums[i] 的值为 0 或 1
 */
public class Solution {

    public boolean kLengthApart(int[] nums, int k) {
        int n = nums.length;
        for (int i = 0, pre = -1; i < n; i++) {
            if (nums[i] == 1) {
                if (pre != -1 && i - pre - 1 < k) {
                    return false;
                }
                pre = i;
            }
        }
        return true;
    }

}
