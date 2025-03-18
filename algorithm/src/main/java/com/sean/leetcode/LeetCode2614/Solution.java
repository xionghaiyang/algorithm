package com.sean.leetcode.LeetCode2614;

/**
 * @Author xionghaiyang
 * @Date 2025-03-18 08:54
 * @Description https://leetcode.cn/problems/prime-in-diagonal
 * 2614. 对角线上的质数
 * 给你一个下标从 0 开始的二维整数数组 nums 。
 * 返回位于 nums 至少一条 对角线 上的最大 质数 。
 * 如果任一对角线上均不存在质数，返回 0 。
 * 注意：
 * 如果某个整数大于 1 ，且不存在除 1 和自身之外的正整数因子，则认为该整数是一个质数。
 * 如果存在整数 i ，使得 nums[i][i] = val 或者 nums[i][nums.length - i - 1]= val ，则认为整数 val 位于 nums 的一条对角线上。
 * 1 <= nums.length <= 300
 * nums.length == numsi.length
 * 1 <= nums[i][j] <= 4*10^6
 */
public class Solution {

    public int diagonalPrime(int[][] nums) {
        int n = nums.length;
        int res = 0;
        for (int i = 0; i < n; i++) {
            if (nums[i][i] > res && isPrime(nums[i][i])) {
                res = nums[i][i];
            }
            if (nums[i][n - i - 1] > res && isPrime(nums[i][n - i - 1])) {
                res = nums[i][n - i - 1];
            }
        }
        return res;
    }

    private boolean isPrime(int num) {
        if (num == 1) {
            return false;
        }
        for (int i = 2; i * i <= num; i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }

}
