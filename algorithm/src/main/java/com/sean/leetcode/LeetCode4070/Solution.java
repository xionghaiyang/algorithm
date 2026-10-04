package com.sean.leetcode.LeetCode4070;

/**
 * @Author: xionghaiyang
 * @Date: 2026-10-04 18:49
 * @Description: https://leetcode.cn/problems/minimum-rotations-to-dial-a-number-i
 * 4070. 拨号的最少旋转次数 I
 * 给你一个长度为 10、由数字组成的字符串 s。
 * 拨号盘上的数字 0 到 9 按顺序排列，且拨号盘是环形的，因此 0 和 9 相邻。
 * 指针最初指向 0。
 * 要按顺序拨出 s 中的每个数字，需要旋转指针，直到它指向该数字。
 * 每次旋转都会将指针移动到一个相邻的数字，你可以向任一方向旋转。
 * 如果指针已经指向要拨出的数字，则无需旋转。
 * 返回拨出 s 中所有数字所需的最少总旋转次数。
 * s.length == 10
 * s 仅由数字 '0' 到 '9' 组成
 */
public class Solution {

    public int minRotations(String s) {
        int res = 0;
        char pre = '0';
        for (char c : s.toCharArray()) {
            res += getCost(pre, c);
            pre = c;
        }
        return res;
    }

    private int getCost(char x, char y) {
        int diff = Math.abs(x - y);
        return Math.min(diff, 10 - diff);
    }

}
