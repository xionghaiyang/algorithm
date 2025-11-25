package com.sean.leetcode.LeetCode1015;

/**
 * @Auther: xionghaiyang
 * @Date: 2023-05-10 08:11
 * @Description: https://leetcode.cn/problems/smallest-integer-divisible-by-k
 * 1015. 可被 K 整除的最小整数
 * 给定正整数 k ，你需要找出可以被 k 整除的、仅包含数字 1 的最 小 正整数 n 的长度。
 * 返回 n 的长度。
 * 如果不存在这样的 n ，就返回-1。
 * 注意： n 不符合 64 位带符号整数。
 * 1 <= k <= 10^5
 */
public class Solution {

    public int smallestRepunitDivByK(int k) {
        if (k % 2 == 0 || k % 5 == 0) {
            return -1;
        }
        for (int i = 1, x = 1 % k; ; i++) {
            if (x == 0) {
                return i;
            }
            x = (x * 10 + 1) % k;
        }
    }

}
