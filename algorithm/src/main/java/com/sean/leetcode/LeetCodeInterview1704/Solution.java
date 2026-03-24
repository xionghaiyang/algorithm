package com.sean.leetcode.LeetCodeInterview1704;

/**
 * @Author xionghaiyang
 * @Date 2026-03-24 20:03
 * @Description https://leetcode.cn/problems/missing-number-lcci
 * 面试题 17.04. 消失的数字
 * 数组nums包含从0到n的所有整数，但其中缺了一个。
 * 请编写代码找出那个缺失的整数。
 * 你有办法在O(n)时间内完成吗？
 * 注意：本题相对书上原题稍作改动
 */
public class Solution {

    public int missingNumber(int[] nums) {
        int n = nums.length;
        int res = 0;
        for (int i = 0; i < n; i++) {
            res ^= i;
            res ^= nums[i];
        }
        res ^= n;
        return res;
    }

}
