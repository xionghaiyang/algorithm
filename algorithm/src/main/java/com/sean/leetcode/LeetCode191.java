package com.sean.leetcode;

/**
 * 位1的个数
 * https://leetcode-cn.com/problems/number-of-1-bits/
 */
public class LeetCode191 {

    //循环检查二进制位
    public static int hammingWeight1(int n) {
        int res = 0;
        for (int i = 0; i < 32; i++) {
            if ((n & (1 << i)) != 0) {
                res++;
            }
        }
        return res;
    }

    //位运算优化
    public static int hammingWeight(int n) {
        int res = 0;
        while (n != 0) {
            n &= n - 1;
            res++;
        }
        return res;
    }

}
