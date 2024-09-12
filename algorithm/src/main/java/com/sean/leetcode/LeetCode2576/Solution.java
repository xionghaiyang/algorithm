package com.sean.leetcode.LeetCode2576;

import java.util.Arrays;

/**
 * @Author xionghaiyang
 * @Date 2024-09-12 09:28
 * @Description https://leetcode.cn/problems/find-the-maximum-number-of-marked-indices/
 * 2576. 求出最多标记下标
 * 给你一个下标从 0 开始的整数数组 nums 。
 * 一开始，所有下标都没有被标记。
 * 你可以执行以下操作任意次：
 * 选择两个 互不相同且未标记 的下标 i 和 j ，满足 2 * nums[i] <= nums[j] ，标记下标 i 和 j 。
 * 请你执行上述操作任意次，返回 nums 中最多可以标记的下标数目。
 */
public class Solution {

    public int maxNumOfMarkedIndices(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        int left = 0, right = n / 2;
        while (left < right) {
            int mid = (left + right + 1) >> 1;
            if (check(nums, mid)) {
                left = mid;
            } else {
                right = mid - 1;
            }
        }
        return left * 2;
    }

    private boolean check(int[] nums, int mid) {
        int n = nums.length;
        for (int i = 0; i < mid; i++) {
            if (nums[i] * 2 > nums[n - mid + i]) {
                return false;
            }
        }
        return true;
    }

    public int maxNumOfMarkedIndices1(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        int left = 0, right = (n + 1) / 2;
        int count = 0;
        while (right < n) {
            if (2 * nums[left] <= nums[right]) {
                count++;
                left++;
            }
            right++;
        }
        return count * 2;
    }

}
