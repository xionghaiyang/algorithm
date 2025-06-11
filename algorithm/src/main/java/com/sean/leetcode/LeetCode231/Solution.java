package com.sean.leetcode.LeetCode231;

/**
 * @Author xionghaiyang
 * @Date 2025-06-11 19:17
 * @Description https://leetcode.cn/problems/power-of-two
 * 231. 2 的幂
 * 给你一个整数 n，请你判断该整数是否是 2 的幂次方。
 * 如果是，返回 true ；否则，返回 false 。
 * 如果存在一个整数 x 使得 n == 2^x ，则认为 n 是 2 的幂次方。
 * -2^31 <= n <= 2^31 - 1
 */
public class Solution {

    public boolean isPowerOfTwo(int n) {
        return n > 0 && (n & (n - 1)) == 0;
    }

    public boolean isPowerOfTwo1(int n) {
        return n > 0 && (n & (-n)) == n;
    }

}
