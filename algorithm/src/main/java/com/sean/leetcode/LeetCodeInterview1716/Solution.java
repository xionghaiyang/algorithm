package com.sean.leetcode.LeetCodeInterview1716;

import java.util.Arrays;

/**
 * @Author xionghaiyang
 * @Date 2026-03-25 21:59
 * @Description https://leetcode.cn/problems/the-masseuse-lcci
 * 面试题 17.16. 按摩师
 * 一个有名的按摩师会收到源源不断的预约请求，每个预约都可以选择接或不接。
 * 在每次预约服务之间要有休息时间，因此她不能接受相邻的预约。
 * 给定一个预约请求序列，替按摩师找到最优的预约集合（总预约时间最长），返回总的分钟数。
 */
public class Solution {

    public int massage(int[] nums) {
        int[] memo = new int[nums.length];
        Arrays.fill(memo, -1);
        return process(nums, memo, 0);
    }

    private int process(int[] nums, int[] memo, int i) {
        if (i >= nums.length) {
            return 0;
        }
        if (memo[i] != -1) {
            return memo[i];
        }
        int res = nums[i] + process(nums, memo, i + 2);
        res = Math.max(res, process(nums, memo, i + 1));
        memo[i] = res;
        return res;
    }

}
