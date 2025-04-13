package com.sean.leetcode.LeetCode1922;

/**
 * @Author xionghaiyang
 * @Date 2025-04-13 09:00
 * @Description https://leetcode.cn/problems/count-good-numbers
 * 1922. 统计好数字的数目
 * 我们称一个数字字符串是 好数字 当它满足（下标从 0 开始）
 * 偶数 下标处的数字为 偶数 且 奇数 下标处的数字为 质数 （2，3，5 或 7）。
 * 比方说，"2582" 是好数字，因为偶数下标处的数字（2 和 8）是偶数且奇数下标处的数字（5 和 2）为质数。
 * 但 "3245" 不是 好数字，因为 3 在偶数下标处但不是偶数。
 * 给你一个整数 n ，请你返回长度为 n 且为好数字的数字字符串 总数 。
 * 由于答案可能会很大，请你将它对 10^9 + 7 取余后返回 。
 * 一个 数字字符串 是每一位都由 0 到 9 组成的字符串，且可能包含前导 0 。
 * 1 <= n <= 10^15
 */
public class Solution {

    long mod = 1_000_000_007;

    public int countGoodNumbers(long n) {
        return (int) (process(5, (n + 1) / 2) * process(4, n / 2) % mod);
    }

    private long process(int x, long y) {
        long res = 1;
        long mul = x;
        while (y > 0) {
            if ((y & 1) != 0) {
                res = res * mul % mod;
            }
            mul = mul * mul % mod;
            y >>= 1;
        }
        return res;
    }

}
