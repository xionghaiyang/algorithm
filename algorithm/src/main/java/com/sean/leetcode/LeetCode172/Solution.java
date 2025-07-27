package com.sean.leetcode.LeetCode172;

/**
 * @Author xionghaiyang
 * @Date 2025-07-27 17:52
 * @Description https://leetcode.cn/problems/factorial-trailing-zeroes
 * 172. 阶乘后的零
 * 给定一个整数 n ，返回 n! 结果中尾随零的数量。
 * 提示 n! = n * (n - 1) * (n - 2) * ... * 3 * 2 * 1
 * 0 <= n <= 10^4
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
