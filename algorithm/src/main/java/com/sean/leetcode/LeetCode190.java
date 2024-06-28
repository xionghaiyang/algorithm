package com.sean.leetcode;

/**
 * 颠倒二进制位
 * https://leetcode-cn.com/problems/reverse-bits/
 */
public class LeetCode190 {

    //逐位颠倒
    public static int reverseBits(int n) {
        int res = 0;
        for (int i = 0; i < 32 && n != 0; i++) {
            res |= (n & 1) << (31 - i);
            n >>>= 1;
        }
        return res;
    }

}
