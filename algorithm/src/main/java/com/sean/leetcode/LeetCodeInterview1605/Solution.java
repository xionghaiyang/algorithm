package com.sean.leetcode.LeetCodeInterview1605;

/**
 * @Author xionghaiyang
 * @Date 2026-03-22 19:45
 * @Description https://leetcode.cn/problems/factorial-zeros-lcci
 * 面试题 16.05. 阶乘尾数
 * 设计一个算法，算出 n 阶乘有多少个尾随零。
 */
public class Solution {

    public int trailingZeroes(int n) {
        int res = 0;
        while (n != 0) {
            n /= 5;
            res += n;
        }
        return res;
    }

}
