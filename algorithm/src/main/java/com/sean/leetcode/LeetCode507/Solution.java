package com.sean.leetcode.LeetCode507;

/**
 * @Author xionghaiyang
 * @Date 2026-04-09 20:21
 * @Description https://leetcode.cn/problems/perfect-number
 * 507. 完美数
 * 对于一个 正整数，如果它和除了它自身以外的所有 正因子 之和相等，我们称它为 「完美数」。
 * 给定一个 整数 n， 如果是完美数，返回 true；否则返回 false。
 * 1 <= num <= 10^8
 */
public class Solution {

    public boolean checkPerfectNumber(int num) {
        if (num == 1) {
            return false;
        }
        int sum = 1;
        for (int p = 2; p * p <= num; p++) {
            if (num % p == 0) {
                sum += p;
                if (p * p < num) {
                    sum += num / p;
                }
            }
        }
        return sum == num;
    }

}
