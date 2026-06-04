package com.sean.leetcode.LeetCode3751;

/**
 * @Author: xionghaiyang
 * @Date: 2026-06-04 07:50
 * @Description: https://leetcode.cn/problems/total-waviness-of-numbers-in-range-i
 * 3751. 范围内总波动值 I
 * 给你两个整数 num1 和 num2，表示一个 闭 区间 [num1, num2]。
 * 一个数字的 波动值 定义为该数字中 峰 和 谷 的总数：
 * 如果一个数位 严格大于 其两个相邻数位，则该数位为 峰。
 * 如果一个数位 严格小于 其两个相邻数位，则该数位为 谷。
 * 数字的第一个和最后一个数位 不能 是峰或谷。
 * 任何少于 3 位的数字，其波动值均为 0。
 * 返回范围 [num1, num2] 内所有数字的波动值之和。
 * 1 <= num1 <= num2 <= 10^5
 */
public class Solution {

    public int totalWaviness(int num1, int num2) {
        int res = 0;
        for (int i = num1; i <= num2; i++) {
            res += getWaviness(i);
        }
        return res;
    }

    private int getWaviness(int x) {
        String s = String.valueOf(x);
        int res = 0;
        for (int i = 1; i < s.length() - 1; i++) {
            if ((s.charAt(i) > s.charAt(i - 1) && s.charAt(i) > s.charAt(i + 1)) || (s.charAt(i) < s.charAt(i - 1) && s.charAt(i) < s.charAt(i + 1))) {
                res++;
            }
        }
        return res;
    }

}
