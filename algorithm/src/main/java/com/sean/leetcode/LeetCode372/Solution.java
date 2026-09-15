package com.sean.leetcode.LeetCode372;

/**
 * @Author: xionghaiyang
 * @Date: 2026-09-15 14:33
 * @Description: https://leetcode.cn/problems/super-pow
 * 372. 超级次方
 * 你的任务是计算 a^b 对 1337 取模，a 是一个正整数，b 是一个非常大的正整数且会以数组形式给出。
 * 1 <= a <= 2^31 - 1
 * 1 <= b.length <= 2000
 * 0 <= b[i] <= 9
 * b 不含前导 0
 */
public class Solution {

    private static final int MOD = 1337;

    public int superPow(int a, int[] b) {
        int n = b.length;
        int res = 1;
        for (int i = n - 1; i >= 0; i--) {
            res = (int) ((long) res * pow(a, b[i]) % MOD);
            a = pow(a, 10);
        }
        return res;
    }

    private int pow(int x, int n) {
        int res = 1;
        while (n != 0) {
            if ((n & 1) != 0) {
                res = (int) ((long) res * x % MOD);
            }
            x = (int) ((long) x * x % MOD);
            n >>= 1;
        }
        return res;
    }

}
