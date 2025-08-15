package com.sean.leetcode.LeetCode1323;

/**
 * @Author xionghaiyang
 * @Date 2025-08-16 06:54
 * @Description https://leetcode.cn/problems/maximum-69-number
 * 1323. 6 和 9 组成的最大数字
 * 给你一个仅由数字 6 和 9 组成的正整数 num。
 * 你最多只能翻转一位数字，将 6 变成 9，或者把 9 变成 6 。
 * 请返回你可以得到的最大数字。
 * 1 <= num <= 10^4
 * num 每一位上的数字都是 6 或者 9 。
 */
public class Solution {

    public int maximum69Number(int num) {
        char[] str = String.valueOf(num).toCharArray();
        int n = str.length;
        int res = num;
        for (int i = 0; i < n; i++) {
            if (str[i] == '6') {
                str[i] = '9';
                res = Integer.parseInt(String.valueOf(str));
                break;
            }
        }
        return res;
    }

}
