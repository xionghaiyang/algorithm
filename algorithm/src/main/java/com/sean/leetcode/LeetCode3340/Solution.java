package com.sean.leetcode.LeetCode3340;

/**
 * @Author xionghaiyang
 * @Date 2025-03-14 07:20
 * @Description https://leetcode.cn/problems/check-balanced-string
 * 3340. 检查平衡字符串
 * 给你一个仅由数字 0 - 9 组成的字符串 num。
 * 如果偶数下标处的数字之和等于奇数下标处的数字之和，则认为该数字字符串是一个 平衡字符串。
 * 如果 num 是一个 平衡字符串，则返回 true；否则，返回 false。
 * 2 <= num.length <= 100
 * num 仅由数字 0 - 9 组成。
 */
public class Solution {

    public boolean isBalanced(String num) {
        int n = num.length();
        int res = 0;
        for (int i = 0; i < n; i++) {
            if ((i & 1) == 0) {
                res += (num.charAt(i) - '0');
            } else {
                res -= (num.charAt(i) - '0');
            }
        }
        return res == 0;
    }

}
