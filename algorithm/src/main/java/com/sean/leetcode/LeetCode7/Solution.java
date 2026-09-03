package com.sean.leetcode.LeetCode7;

/**
 * @Author: xionghaiyang
 * @Date: 2026-09-03 18:36
 * @Description: https://leetcode.cn/problems/reverse-integer
 * 7. 整数反转
 * 给你一个 32 位的有符号整数 x ，返回将 x 中的数字部分反转后的结果。
 * 如果反转后整数超过 32 位的有符号整数的范围 [−2^31,  2^31 − 1] ，就返回 0。
 * 假设环境不允许存储 64 位整数（有符号或无符号）。
 * -2^31 <= x <= 2^31 - 1
 */
public class Solution {

    public int reverse(int x) {
        int res = 0, last = 0;
        while (x != 0) {
            last = res;
            res = res * 10 + x % 10;
            if (last != res / 10) {
                return 0;
            }
            x /= 10;
        }
        return res;
    }

}
