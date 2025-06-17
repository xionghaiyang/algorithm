package com.sean.leetcode.LeetCode2966;

import java.util.Arrays;

/**
 * @Author xionghaiyang
 * @Date 2025-06-18 06:19
 * @Description https://leetcode.cn/problems/divide-array-into-arrays-with-max-difference
 * 2966. 划分数组并满足最大差限制
 * 给你一个长度为 n 的整数数组 nums，以及一个正整数 k 。
 * 将这个数组划分为 n / 3 个长度为 3 的子数组，并满足以下条件：
 * 子数组中 任意 两个元素的差必须 小于或等于 k 。
 * 返回一个 二维数组 ，包含所有的子数组。
 * 如果不可能满足条件，就返回一个空数组。
 * 如果有多个答案，返回 任意一个 即可。
 * n == nums.length
 * 1 <= n <= 10^5
 * n 是 3 的倍数
 * 1 <= nums[i] <= 10^5
 * 1 <= k <= 10^5
 */
public class Solution {

    public int[][] divideArray(int[] nums, int k) {
        Arrays.sort(nums);
        int n = nums.length;
        int[][] res = new int[n / 3][];
        for (int i = 0, j = 0; i < n / 3 && j < n; i++, j += 3) {
            if (nums[j + 2] - nums[j] <= k) {
                res[i] = new int[]{nums[j], nums[j + 1], nums[j + 2]};
            } else {
                return new int[][]{};
            }
        }
        return res;
    }

}
