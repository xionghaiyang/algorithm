package com.sean.leetcode.LeetCode4071;

/**
 * @Author: xionghaiyang
 * @Date: 2026-10-04 18:54
 * @Description: https://leetcode.cn/problems/minimum-rotations-to-dial-a-number-ii
 * 4071. 拨号的最少旋转次数 II
 * 给你一个整数 n 和一个长度为 n、由数字组成的字符串 s。
 * 拨号盘上的数字 0 到 9 按顺序排列，且拨号盘是环形的，因此 0 和 9 相邻。
 * 指针最初指向 0。
 * 要按顺序拨出 s 中的每个数字，需要旋转指针，直到它指向该数字。
 * 每次旋转都会将指针移动到一个相邻的数字，你可以向任一方向旋转。
 * 如果指针已经指向要拨出的数字，则无需旋转。
 * 在拨号之前，你可以执行以下操作至多一次：
 * 选择一个满足 0 <= k < n 的下标 k，并反转后缀 s[k..n - 1]。
 * 通过最优地选择是否执行该操作以及反转哪个后缀，返回拨出操作后的字符串所需的最少总旋转次数。
 * 字符串的后缀是从字符串中的任意位置开始、延伸到字符串末尾的连续字符序列。
 * 1 <= n == s.length <= 10^5​​​​​​​
 * s 仅由数字 '0' 到 '9' 组成
 */
public class Solution {

    public int minRotations(int n, String s) {
        char[] str = s.toCharArray();
        int suf = 0;
        for (int i = 1; i < n; i++) {
            suf += getCost(str[i - 1], str[i]);
        }
        int res = getCost('0', str[n - 1]) + suf;
        int pre = getCost('0', str[0]);
        for (int k = 1; k < n; k++) {
            int op = getCost(str[k - 1], str[k]);
            suf -= op;
            res = Math.min(res, pre + getCost(str[k - 1], str[n - 1]) + suf);
            pre += op;
        }
        return res;
    }

    private int getCost(char x, char y) {
        int diff = Math.abs(x - y);
        return Math.min(diff, 10 - diff);
    }

}
