package com.sean.leetcode.LeetCode1295;

/**
 * @Author xionghaiyang
 * @Date 2025-04-30 08:53
 * @Description https://leetcode.cn/problems/find-numbers-with-even-number-of-digits
 * 1295. 统计位数为偶数的数字
 * 给你一个整数数组 nums，请你返回其中包含 偶数 个数位的数字的个数。
 * 1 <= nums.length <= 500
 * 1 <= nums[i] <= 10^5
 */
public class Solution {

    public int findNumbers(int[] nums) {
        int res = 0;
        for (int num : nums) {
            res += (String.valueOf(num).length() & 1) == 0 ? 1 : 0;
        }
        return res;
    }

    public int findNumbers1(int[] nums) {
        int res = 0;
        for (int num : nums) {
            while (num >= 100) {
                num /= 100;
            }
            if (num >= 10) {
                res++;
            }
        }
        return res;
    }

}
