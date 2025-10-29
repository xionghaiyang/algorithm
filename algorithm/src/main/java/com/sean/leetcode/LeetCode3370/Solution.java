package com.sean.leetcode.LeetCode3370;

/**
 * @Author xionghaiyang
 * @Date 2025-10-29 12:28
 * @Description https://leetcode.cn/problems/smallest-number-with-all-set-bits
 * 3370. 仅含置位位的最小整数
 * 给你一个正整数 n。
 * 返回 大于等于 n 且二进制表示仅包含 置位 位的 最小 整数 x 。
 * 置位 位指的是二进制表示中值为 1 的位。
 * 1 <= n <= 1000
 */
public class Solution {

    public int smallestNumber(int n) {
        return (Integer.highestOneBit(n) << 1) - 1;
    }

}
