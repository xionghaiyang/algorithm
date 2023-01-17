package com.sean.leetcode.LeetCode2293;

/**
 * @Auther: xionghaiyang
 * @Date: 2023-01-17 10:07
 * @Description: https://leetcode.cn/problems/min-max-game/
 * 2293. 极大极小游戏
 * 给你一个下标从 0 开始的整数数组 nums ，其长度是 2 的幂。
 * 对 nums 执行下述算法：
 * 设 n 等于 nums 的长度，如果 n == 1 ，终止 算法过程。
 * 否则，创建 一个新的整数数组 newNums ，新数组长度为 n / 2 ，下标从 0 开始。
 * 对于满足 0 <= i < n / 2 的每个 偶数 下标 i ，将 newNums[i] 赋值 为 min(nums[2 * i], nums[2 * i + 1]) 。
 * 对于满足 0 <= i < n / 2 的每个 奇数 下标 i ，将 newNums[i] 赋值 为 max(nums[2 * i], nums[2 * i + 1]) 。
 * 用 newNums 替换 nums 。
 * 从步骤 1 开始 重复 整个过程。
 * 执行算法后，返回 nums 中剩下的那个数字。
 */
public class Solution {

    public int minMaxGame(int[] nums) {
        return process(nums)[0];
    }

    private int[] process(int[] nums) {
        if (nums.length == 1) {
            return nums;
        }
        int n = nums.length;
        int[] res = new int[n / 2];
        for (int i = 0; i < n / 2; i++) {
            if (i % 2 == 0) {
                res[i] = Math.min(nums[2 * i], nums[2 * i + 1]);
            } else {
                res[i] = Math.max(nums[2 * i], nums[2 * i + 1]);
            }
        }
        return process(res);
    }

}
