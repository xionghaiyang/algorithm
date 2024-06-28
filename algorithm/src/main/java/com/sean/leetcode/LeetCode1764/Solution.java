package com.sean.leetcode.LeetCode1764;

/**
 * @Auther: xionghaiyang
 * @Date: 2022-12-19 09:26
 * @Description: https://leetcode.cn/problems/form-array-by-concatenating-subarrays-of-another-array/
 * 1764. 通过连接另一个数组的子数组得到一个数组
 * 给你一个长度为 n 的二维整数数组 groups ，同时给你一个整数数组 nums 。
 * 你是否可以从 nums 中选出 n 个 不相交 的子数组，使得第 i 个子数组与 groups[i] （下标从 0 开始）完全相同，
 * 且如果 i > 0 ，那么第 (i-1) 个子数组在 nums 中出现的位置在第 i 个子数组前面。
 * （也就是说，这些子数组在 nums 中出现的顺序需要与 groups 顺序相同）
 * 如果你可以找出这样的 n 个子数组，请你返回 true ，否则返回 false 。
 * 如果不存在下标为 k 的元素 nums[k] 属于不止一个子数组，就称这些子数组是 不相交 的。
 * 子数组指的是原数组中连续元素组成的一个序列。
 */
public class Solution {

    public boolean canChoose(int[][] groups, int[] nums) {
        int i = 0;
        for (int k = 0; k < nums.length && i < groups.length; ) {
            if (check(groups[i], nums, k)) {
                k += groups[i].length;
                i++;
            } else {
                k++;
            }
        }
        return i == groups.length;
    }

    private boolean check(int[] group, int[] nums, int k) {
        if (k + group.length > nums.length) {
            return false;
        }
        for (int j = 0; j < group.length; j++) {
            if (group[j] != nums[k + j]) {
                return false;
            }
        }
        return true;
    }

}
