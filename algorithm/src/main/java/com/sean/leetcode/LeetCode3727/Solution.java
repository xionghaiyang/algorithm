package com.sean.leetcode.LeetCode3727;

import java.util.Arrays;

/**
 * @Author: xionghaiyang
 * @Date: 2026-10-13 06:11
 * @Description: https://leetcode.cn/problems/maximum-alternating-sum-of-squares
 * 3727. 最大交替平方和
 * 给你一个整数数组 nums。
 * 你可以以任意顺序 重新排列元素 。
 * 数组 arr 的 交替得分 定义为：
 * score = arr[0]2 - arr[1]2 + arr[2]2 - arr[3]2 + ...
 * 在对 nums 重新排列后，返回其 最大可能的交替得分。
 * 1 <= nums.length <= 10^5
 * -4 * 104 <= nums[i] <= 4 * 10^4
 */
public class Solution {

    public long maxAlternatingSum(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            nums[i] *= nums[i];
        }
        Arrays.sort(nums);
        long res = 0;
        int mid = n >> 1;
        for (int i = 0; i < mid; i++) {
            res -= nums[i];
        }
        for (int i = mid; i < n; i++) {
            res += nums[i];
        }
        return res;
    }

}
