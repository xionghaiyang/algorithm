package com.sean.leetcode.LeetCode1250;

/**
 * @Auther: xionghaiyang
 * @Date: 2023-02-15 08:23
 * @Description: https://leetcode.cn/problems/check-if-it-is-a-good-array/
 * 1250. 检查「好数组」
 * 给你一个正整数数组 nums，你需要从中任选一些子集，然后将子集中每一个数乘以一个 任意整数，并求出他们的和。
 * 假如该和结果为 1，那么原数组就是一个「好数组」，则返回 True；否则请返回 False。
 */
public class Solution {

    public boolean isGoodArray(int[] nums) {
        int divisor = nums[0];
        for (int num : nums) {
            divisor = gcd(divisor, num);
            if (divisor == 1) {
                break;
            }
        }
        return divisor == 1;
    }

    private int gcd(int num1, int num2) {
        while (num2 != 0) {
            int temp = num1;
            num1 = num2;
            num2 = temp % num2;
        }
        return num1;
    }

}
