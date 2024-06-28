package com.sean.leetcode;

/**
 * @Auther: xionghaiyang
 * @Date: 2022/3/25 14:01
 * @Description: https://leetcode-cn.com/problems/factorial-trailing-zeroes/阶乘后的零
 * @version: 1.0
 */
public class LeetCode172 {

    public int trailingZeroes(int n) {
        int res = 0;
        for (int i = 5; i <= n; i += 5) {
            for (int j = i; j % 5 == 0; j /= 5) {
                res++;
            }
        }
        return res;
    }
}
