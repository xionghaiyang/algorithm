package com.sean.leetcode.LeetCode1031;

/**
 * @Auther: xionghaiyang
 * @Date: 2023-04-26 08:11
 * @Description: https://leetcode.cn/problems/maximum-sum-of-two-non-overlapping-subarrays/
 * 1031. 两个非重叠子数组的最大和
 * 给你一个整数数组 nums 和两个整数 firstLen 和 secondLen，
 * 请你找出并返回两个非重叠 子数组 中元素的最大和，长度分别为 firstLen 和 secondLen 。
 * 长度为 firstLen 的子数组可以出现在长为 secondLen 的子数组之前或之后，但二者必须是不重叠的。
 * 子数组是数组的一个 连续 部分。
 */
public class Solution {

    public int maxSumTwoNoOverlap(int[] nums, int firstLen, int secondLen) {
        return Math.max(process(nums, firstLen, secondLen), process(nums, secondLen, firstLen));
    }

    private int process(int[] nums, int firstLen, int secondLen) {
        int n = nums.length;
        int sumLeft = 0;
        for (int i = 0; i < firstLen; i++) {
            sumLeft += nums[i];
        }
        int maxSumLeft = sumLeft;
        int sumRight = 0;
        for (int i = firstLen; i < firstLen + secondLen; i++) {
            sumRight += nums[i];
        }
        int res = maxSumLeft + sumRight;
        for (int i = firstLen + secondLen, j = firstLen; i < n; i++, j++) {
            sumLeft += nums[j] - nums[j - firstLen];
            maxSumLeft = Math.max(maxSumLeft, sumLeft);
            sumRight += nums[i] - nums[i - secondLen];
            res = Math.max(res, maxSumLeft + sumRight);
        }
        return res;
    }

}
