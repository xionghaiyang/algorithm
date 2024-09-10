package com.sean.leetcode.LeetCode2552;

/**
 * @Author xionghaiyang
 * @Date 2024-09-10 14:22
 * @Description https://leetcode.cn/problems/count-increasing-quadruplets/
 * 2552. 统计上升四元组
 * 给你一个长度为 n 下标从 0 开始的整数数组 nums ，它包含 1 到 n 的所有数字，请你返回上升四元组的数目。
 * 如果一个四元组 (i, j, k, l) 满足以下条件，我们称它是上升的：
 * 0 <= i < j < k < l < n 且 nums[i] < nums[k] < nums[j] < nums[l] 。
 */
public class Solution {

    public long countQuadruplets(int[] nums) {
        int n = nums.length;
        int[] pre = new int[n + 1];
        long res = 0;
        for (int j = 0; j < n; j++) {
            int suf = 0;
            for (int k = n - 1; k > j; k--) {
                if (nums[j] > nums[k]) {
                    res += (long) pre[nums[k]] * suf;
                } else {
                    suf++;
                }
            }
            for (int x = nums[j] + 1; x <= n; x++) {
                pre[x]++;
            }
        }
        return res;
    }

}
