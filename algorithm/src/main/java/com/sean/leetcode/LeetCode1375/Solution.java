package com.sean.leetcode.LeetCode1375;

/**
 * @Auther: xionghaiyang
 * @Date: 2023-06-14 11:15
 * @Description: https://leetcode.cn/problems/number-of-times-binary-string-is-prefix-aligned/
 * 1375. 二进制字符串前缀一致的次数
 * 给你一个长度为 n 、下标从 1 开始的二进制字符串，所有位最开始都是 0 。
 * 我们会按步翻转该二进制字符串的所有位（即，将 0 变为 1）。
 * 给你一个下标从 1 开始的整数数组 flips ，
 * 其中 flips[i] 表示对应下标 i 的位将会在第 i 步翻转。
 * 二进制字符串 前缀一致 需满足：在第 i 步之后，在 闭 区间 [1, i] 内的所有位都是 1 ，而其他位都是 0 。
 * 返回二进制字符串在翻转过程中 前缀一致 的次数。
 */
public class Solution {

    public int numTimesAllBlue(int[] flips) {
        if (flips == null || flips.length == 0) {
            return 0;
        }
        int n = flips.length;
        int res = 0;
        int right = 0;
        for (int i = 0; i < n; i++) {
            right = Math.max(right, flips[i]);
            if (right == i + 1) {
                res++;
            }
        }
        return res;
    }

}
