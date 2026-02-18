package com.sean.leetcode.LeetCode693;

/**
 * @Author xionghaiyang
 * @Date 2026-02-18 08:52
 * @Description https://leetcode.cn/problems/binary-number-with-alternating-bits
 * 693. 交替位二进制数
 * 给定一个正整数，检查它的二进制表示是否总是 0、1 交替出现：换句话说，就是二进制表示中相邻两位的数字永不相同。
 * 1 <= n <= 2^31 - 1
 */
public class Solution {

    public boolean hasAlternatingBits(int n) {
        int x = n ^ (n >> 1);
        return (x & (x + 1)) == 0;
    }

}
