package com.sean.leetcode.LeetCode1984;

import java.util.Arrays;

/**
 * @Author xionghaiyang
 * @Date 2026-01-25 10:46
 * @Description https://leetcode.cn/problems/minimum-difference-between-highest-and-lowest-of-k-scores
 * 1984. 学生分数的最小差值
 * 给你一个 下标从 0 开始 的整数数组 nums ，其中 nums[i] 表示第 i 名学生的分数。
 * 另给你一个整数 k 。
 * 从数组中选出任意 k 名学生的分数，使这 k 个分数间 最高分 和 最低分 的 差值 达到 最小化 。
 * 返回可能的 最小差值 。
 * 1 <= k <= nums.length <= 1000
 * 0 <= nums[i] <= 10^5
 */
public class Solution {

    public int minimumDifference(int[] nums, int k) {
        int n = nums.length;
        Arrays.sort(nums);
        int res = Integer.MAX_VALUE;
        for (int i = 0; i + k - 1 < n; i++) {
            res = Math.min(res, nums[i + k - 1] - nums[i]);
        }
        return res;
    }

}
