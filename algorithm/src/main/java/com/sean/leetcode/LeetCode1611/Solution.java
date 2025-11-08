package com.sean.leetcode.LeetCode1611;

/**
 * @Author xionghaiyang
 * @Date 2025-11-08 22:54
 * @Description https://leetcode.cn/problems/minimum-one-bit-operations-to-make-integers-zero
 * 1611. 使整数变为 0 的最少操作次数
 * 给你一个整数 n，你需要重复执行多次下述操作将其转换为 0 ：
 * 翻转 n 的二进制表示中最右侧位（第 0 位）。
 * 如果第 (i-1) 位为 1 且从第 (i-2) 位到第 0 位都为 0，则翻转 n 的二进制表示中的第 i 位。
 * 返回将 n 转换为 0 的最小操作次数。
 * 0 <= n <= 10^9
 */
public class Solution {

    public int minimumOneBitOperations(int n) {
        int res = 0;
        for (int i = 29, sign = 1; i >= 0; i--) {
            if ((n & (1 << i)) != 0) {
                res += sign * ((1 << (i + 1)) - 1);
                sign = -sign;
            }
        }
        return res;
    }

}
