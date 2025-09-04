package com.sean.leetcode.LeetCode2749;

/**
 * @Author xionghaiyang
 * @Date 2025-09-05 06:14
 * @Description https://leetcode.cn/problems/minimum-operations-to-make-the-integer-zero
 * 2749. 得到整数零需要执行的最少操作数
 * 给你两个整数：num1 和 num2 。
 * 在一步操作中，你需要从范围 [0, 60] 中选出一个整数 i ，并从 num1 减去 2^i + num2 。
 * 请你计算，要想使 num1 等于 0 需要执行的最少操作数，并以整数形式返回。
 * 如果无法使 num1 等于 0 ，返回 -1 。
 * 1 <= num1 <= 10^9
 * -10^9 <= num2 <= 10^9
 */
public class Solution {

    public int makeTheIntegerZero(int num1, int num2) {
        for (long k = 1; k <= num1 - num2 * k; k++) {
            if (k >= Long.bitCount(num1 - num2 * k)) {
                return (int) k;
            }
        }
        return -1;
    }

}
