package com.sean.leetcode.LeetCode3289;

/**
 * @Author xionghaiyang
 * @Date 2025-10-31 12:21
 * @Description https://leetcode.cn/problems/the-two-sneaky-numbers-of-digitville
 * 3289. 数字小镇中的捣蛋鬼
 * 数字小镇 Digitville 中，存在一个数字列表 nums，其中包含从 0 到 n - 1 的整数。
 * 每个数字本应 只出现一次，然而，有 两个 顽皮的数字额外多出现了一次，使得列表变得比正常情况下更长。
 * 为了恢复 Digitville 的和平，作为小镇中的名侦探，请你找出这两个顽皮的数字。
 * 返回一个长度为 2 的数组，包含这两个数字（顺序任意）。
 * 2 <= n <= 100
 * nums.length == n + 2
 * 0 <= nums[i] < n
 * 输入保证 nums 中 恰好 包含两个重复的元素。
 */
public class Solution {

    public int[] getSneakyNumbers(int[] nums) {
        int n = nums.length - 2;
        int[] res = new int[2];
        int i = 0;
        boolean[] has = new boolean[n];
        for (int num : nums) {
            if (has[num]) {
                res[i++] = num;
                if (i == 2) {
                    break;
                }
            } else {
                has[num] = true;
            }
        }
        return res;
    }

}
