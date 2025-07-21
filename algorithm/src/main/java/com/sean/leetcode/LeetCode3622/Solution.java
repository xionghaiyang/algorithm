package com.sean.leetcode.LeetCode3622;

/**
 * @Author xionghaiyang
 * @Date 2025-07-21 17:49
 * @Description https://leetcode.cn/problems/check-divisibility-by-digit-sum-and-product
 * 3622. 判断整除性
 * 给你一个正整数 n。
 * 请判断 n 是否可以被以下两值之和 整除：
 * n 的 数字和（即其各个位数之和）。
 * n 的 数字积（即其各个位数之积）。
 * 如果 n 能被该和整除，返回 true；否则，返回 false。
 * 1 <= n <= 10^6
 */
public class Solution {

    public boolean checkDivisibility(int n) {
        int sum = 0, product = 1;
        for (int x = n; x > 0; x /= 10) {
            int d = x % 10;
            sum += d;
            product *= d;
        }
        return n % (sum + product) == 0;
    }

}
