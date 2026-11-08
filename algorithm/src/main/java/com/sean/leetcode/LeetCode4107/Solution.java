package com.sean.leetcode.LeetCode4107;

/**
 * @Author: xionghaiyang
 * @Date: 2026-11-08 18:04
 * @Description: https://leetcode.cn/problems/count-valid-suffix-sorts-i
 * 4107. 统计有效后缀排序的数量 I
 * 给你两个长度均为 n 的整数数组 nums 和 target。
 * 对于每个下标 i，考虑将原数组的 后缀 nums[i..n - 1] 按 非递增顺序 排序。
 * 返回使排序后的数组等于 target 的下标 i 的数量。
 * 子数组 是数组中一个连续的 非空 元素序列。
 * 数组的 后缀 是从任意下标开始并延伸至数组末尾的子数组。
 * 1 <= n == nums.length == target.length <= 10^5
 * 1 <= nums[i], target[i] <= 10​​​​​​​^5​​​​​​​​​​​​​​
 */
public class Solution {

    public int countSuffix(int[] nums, int[] target) {
        int n = nums.length;
        int max = 0;
        for (int x : nums) {
            max = Math.max(max, x);
        }
        for (int x : target) {
            max = Math.max(max, x);
        }
        int[] diff = new int[max + 1];
        for (int i = 0; i < n; i++) {
            diff[nums[i]]++;
            diff[target[i]]--;
        }
        for (int d : diff) {
            if (d != 0) {
                return 0;
            }
        }
        int maxI = n - 1;
        for (int i = 0; i < n; i++) {
            if (nums[i] != target[i]) {
                maxI = i;
                break;
            }
        }
        int minI = 0;
        for (int i = n - 1; i > 0; i--) {
            if (target[i - 1] < target[i]) {
                minI = i;
                break;
            }
        }
        return Math.max(maxI - minI + 1, 0);
    }

}
