package com.sean.leetcode.LeetCode1009;

/**
 * @Author xionghaiyang
 * @Date 2026-03-11 08:36
 * @Description https://leetcode.cn/problems/complement-of-base-10-integer
 * 1009. 十进制整数的反码
 * 每个非负整数 N 都有其二进制表示。
 * 例如， 5 可以被表示为二进制 "101"，11 可以用二进制 "1011" 表示，依此类推。
 * 注意，除 N = 0 外，任何二进制表示中都不含前导零。
 * 二进制的反码表示是将每个 1 改为 0 且每个 0 变为 1。
 * 例如，二进制数 "101" 的二进制反码为 "010"。
 * 给你一个十进制数 N，请你返回其二进制表示的反码所对应的十进制整数。
 * 0 <= N < 10^9
 */
public class Solution {

    public int bitwiseComplement(int n) {
        if (n == 0) {
            return 1;
        }
        int w = 32 - Integer.numberOfLeadingZeros(n);
        return ((1 << w) - 1) ^ n;
    }

}
