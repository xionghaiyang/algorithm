package com.sean.leetcode.LeetCode326;

/**
 * @Author xionghaiyang
 * @Date 2025-06-12 13:58
 * @Description https://leetcode.cn/problems/power-of-three
 * 326. 3 的幂
 * 给定一个整数，写一个函数来判断它是否是 3 的幂次方。
 * 如果是，返回 true ；否则，返回 false 。
 * 整数 n 是 3 的幂次方需满足：存在整数 x 使得 n == 3^x
 * -2^31 <= n <= 2^31 - 1
 */
public class Solution {

    public boolean isPowerOfThree(int n) {
        if (n <= 0) {
            return false;
        }
        while (n % 3 == 0) {
            n /= 3;
        }
        return n == 1;
    }

}
