package com.sean.leetcode.LeetCode4092;

/**
 * @Author: xionghaiyang
 * @Date: 2026-10-25 17:08
 * @Description: https://leetcode.cn/problems/binary-digit-score
 * 4092. 二进制位得分
 * 给你两个整数 a 和 b。
 * 设 s1 和 s2 分别为 a 和 b 的二进制表示中置位的数量。
 * 这两个整数的得分定义如下：
 * 如果 s1 >= s2，则得分为 s1 * s2。
 * 否则，得分为 s2 / s1，其中除法采用整数除法。
 * 返回一个整数，表示 a 和 b 的得分。
 * 置位是指数的二进制表示中值为 1 的位。
 * 1 <= a, b <= 100
 */
public class Solution {

    public int calculateScore(int a, int b) {
        int s1 = Integer.bitCount(a), s2 = Integer.bitCount(b);
        return s1 >= s2 ? s1 * s2 : s2 / s1;
    }

}
