package com.sean.leetcode.LeetCode1871;

/**
 * @Author: xionghaiyang
 * @Date: 2026-05-26 16:41
 * @Description: https://leetcode.cn/problems/jump-game-vii
 * 1871. 跳跃游戏 VII
 * 给你一个下标从 0 开始的二进制字符串 s 和两个整数 minJump 和 maxJump 。
 * 一开始，你在下标 0 处，且该位置的值一定为 '0' 。
 * 当同时满足如下条件时，你可以从下标 i 移动到下标 j 处：
 * i + minJump <= j <= min(i + maxJump, s.length - 1) 且
 * s[j] == '0'.
 * 如果你可以到达 s 的下标 s.length - 1 处，请你返回 true ，否则返回 false 。
 * 2 <= s.length <= 10^5
 * s[i] 要么是 '0' ，要么是 '1'
 * s[0] == '0'
 * 1 <= minJump <= maxJump < s.length
 */
public class Solution {

    public boolean canReach(String s, int minJump, int maxJump) {
        int n = s.length();
        boolean[] canReaches = new boolean[n];
        canReaches[0] = true;
        for (int i = 0, j = 1; i < n && j < n; i++) {
            if (canReaches[i] && s.charAt(i) == '0') {
                for (j = Math.max(j, i + minJump); j <= Math.min(i + maxJump, n - 1); j++) {
                    canReaches[j] = true;
                }
            }
        }
        return canReaches[n - 1] && s.charAt(n - 1) == '0';
    }

}
