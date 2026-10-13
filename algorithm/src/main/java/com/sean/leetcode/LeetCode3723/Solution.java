package com.sean.leetcode.LeetCode3723;

/**
 * @Author: xionghaiyang
 * @Date: 2026-10-14 04:45
 * @Description: https://leetcode.cn/problems/maximize-sum-of-squares-of-digits
 * 3723. 数位平方和的最大值
 * 给你两个 正 整数 num 和 sum。
 * 如果一个正整数 n 满足以下两个条件，则称其为 好整数 ：
 * n 的位数 恰好 为 num。
 * n 的各位数字之和 恰好 为 sum。
 * 一个 好整数 n 的 分数 定义为 n 的各位数字的平方和。
 * 返回一个 字符串 ，表示能获得 最大分数 的 好整数 n。
 * 如果有多个可能的整数，返回 最大 的那个。
 * 如果不存在这样的整数，返回一个空字符串。
 * 1 <= num <= 2 * 10^5
 * 1 <= sum <= 2 * 10^6
 */
public class Solution {

    public String maxSumOfSquares(int num, int sum) {
        if (num * 9 < sum) {
            return "";
        }
        StringBuilder res = new StringBuilder();
        int cnt = sum / 9, remainder = sum % 9;
        for (int i = 0; i < cnt; i++) {
            res.append('9');
        }
        if (remainder > 0) {
            res.append(remainder);
        }
        num -= res.length();
        for (int i = 0; i < num; i++) {
            res.append('0');
        }
        return res.toString();
    }

}
