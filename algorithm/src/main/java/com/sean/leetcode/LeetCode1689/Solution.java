package com.sean.leetcode.LeetCode1689;

/**
 * @Author xionghaiyang
 * @Date 2026-03-01 07:16
 * @Description https://leetcode.cn/problems/partitioning-into-minimum-number-of-deci-binary-numbers
 * 1689. 十-二进制数的最少数目
 * 如果一个十进制数字不含任何前导零，且每一位上的数字不是 0 就是 1 ，那么该数字就是一个 十-二进制数 。
 * 例如，101 和 1100 都是 十-二进制数，而 112 和 3001 不是。
 * 给你一个表示十进制整数的字符串 n ，返回和为 n 的 十-二进制数 的最少数目。
 * 1 <= n.length <= 10^5
 * n 仅由数字组成
 * n 不含任何前导零并总是表示正整数
 */
public class Solution {

    public int minPartitions(String n) {
        int res = 0;
        for (char c : n.toCharArray()) {
            res = Math.max(res, c - '0');
        }
        return res;
    }

}
