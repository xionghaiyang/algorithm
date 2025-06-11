package com.sean.leetcode.LeetCode1281;

/**
 * @Author xionghaiyang
 * @Date 2025-06-11 18:55
 * @Description https://leetcode.cn/problems/subtract-the-product-and-sum-of-digits-of-an-integer
 * 1281. 整数的各位积和之差
 * 给你一个整数 n，请你帮忙计算并返回该整数「各位数字之积」与「各位数字之和」的差。
 * 1 <= n <= 10^5
 */
public class Solution {

    public int subtractProductAndSum(int n) {
        int p = 1, s = 0;
        while (n > 0) {
            int d = n % 10;
            p *= d;
            s += d;
            n /= 10;
        }
        return p - s;
    }

}
