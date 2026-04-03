package com.sean.leetcode.LeetCode3871;

/**
 * @Author xionghaiyang
 * @Date 2026-04-03 12:35
 * @Description https://leetcode.cn/problems/count-commas-in-range-ii
 * 3871. 统计范围内的逗号 II
 * 给你一个整数 n。
 * 返回将所有从 [1, n]（包含两端）范围内的整数以 标准 数字格式书写时所用到的 逗号总数。
 * 在 标准 格式中：
 * 从右边开始，每 三位 数字后插入一个逗号。
 * 位数 少于四位 的数字不包含逗号。
 * 1 <= n <= 10^15
 */
public class Solution {

    public long countCommas(long n) {
        long res = 0;
        for (long i = 1000; i <= n; i *= 1000) {
            res += n - i + 1;
        }
        return res;
    }

}
