package com.sean.leetcode.LeetCode1979;

/**
 * @Author: xionghaiyang
 * @Date: 2026-07-18 08:08
 * @Description: https://leetcode.cn/problems/find-greatest-common-divisor-of-array
 * 1979. 找出数组的最大公约数
 * 给你一个整数数组 nums ，返回数组中最大数和最小数的 最大公约数 。
 * 两个数的 最大公约数 是能够被两个数整除的最大正整数。
 * 2 <= nums.length <= 1000
 * 1 <= nums[i] <= 1000
 */
public class Solution {

    public int findGCD(int[] nums) {
        int min = 1000, max = 1;
        for (int num : nums) {
            min = Math.min(min, num);
            max = Math.max(max, num);
        }
        return gcd(max, min);
    }

    private int gcd(int a, int b) {
        return a != 0 ? gcd(b % a, a) : b;
    }

}
