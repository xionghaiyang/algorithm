package com.sean.leetcode;

/**
 * 汉明距离
 * https://leetcode-cn.com/problems/hamming-distance/
 */
public class LeetCode461 {

    //内置位计数功能
    public static int hammingDistance1(int x, int y) {
        return Integer.bitCount(x ^ y);
    }

    //移位实现位计数
    public static int hammingDistance2(int x, int y) {
        int s = x ^ y, res = 0;
        while (s != 0) {
            res += s & 1;
            s >>= 1;
        }
        return res;
    }

    //Brian Kernighan 算法
    public static int hammingDistance(int x, int y) {
        int s = x ^ y, res = 0;
        while (s != 0) {
            s &= s - 1;
            res++;
        }
        return res;
    }

}
