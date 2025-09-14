package com.sean.leetcode.LeetCode3684;

import java.util.Arrays;

/**
 * @Author xionghaiyang
 * @Date 2025-09-14 18:25
 * @Description https://leetcode.cn/problems/maximize-sum-of-at-most-k-distinct-elements
 * 3684. 至多 K 个不同元素的最大和
 * 给你一个 正整数 数组 nums 和一个整数 k。
 * 从 nums 中选择最多 k 个元素，使它们的和最大化。
 * 但是，所选的数字必须 互不相同 。
 * 返回一个包含所选数字的数组，数组中的元素按 严格递减 顺序排序。
 * 1 <= nums.length <= 100
 * 1 <= nums[i] <= 10^9
 * 1 <= k <= nums.length
 */
public class Solution {

    public int[] maxKDistinct(int[] nums, int k) {
        Arrays.sort(nums);
        int uniques = process(nums);
        int size = Math.min(uniques, k);
        int[] res = new int[size];
        for (int i = 0; i < size; i++) {
            res[i] = nums[uniques - 1 - i];
        }
        return res;
    }

    private int process(int[] nums) {
        int k = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != nums[i - 1]) {
                nums[k++] = nums[i];
            }
        }
        return k;
    }

}
