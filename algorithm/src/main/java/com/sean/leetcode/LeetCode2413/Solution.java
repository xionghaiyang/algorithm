package com.sean.leetcode.LeetCode2413;

/**
 * @Auther: xionghaiyang
 * @Date: 2023-04-21 08:13
 * @Description: https://leetcode.cn/problems/smallest-even-multiple/
 * 2413. 最小偶倍数
 * 给你一个正整数 n ，返回 2 和 n 的最小公倍数（正整数）。
 * 1 <= n <= 150
 */
public class Solution {

    public int smallestEvenMultiple(int n) {
        return n % 2 == 0 ? n : 2 * n;
    }

}
