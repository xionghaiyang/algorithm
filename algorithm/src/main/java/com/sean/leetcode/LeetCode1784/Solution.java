package com.sean.leetcode.LeetCode1784;

/**
 * @Auther: xionghaiyang
 * @Date: 2022-10-03 22:16
 * @Description: https://leetcode.cn/problems/check-if-binary-string-has-at-most-one-segment-of-ones/
 * 1784. 检查二进制字符串字段
 * 给你一个二进制字符串 s ，该字符串 不含前导零 。
 * 如果 s 包含 零个或一个由连续的 '1' 组成的字段 ，返回 true​​​ 。否则，返回 false 。
 * 如果 s 中 由连续若干个 '1' 组成的字段 数量不超过 1，返回 true​​​ 。否则，返回 false 。
 */
public class Solution {

    public boolean checkOnesSegment(String s) {
        int n = s.length();
        int left = -1;
        int count = 0;
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (c == '1') {
                if (i == left + 1) {
                    count++;
                }
            } else {
                left = i;
            }
        }
        return count <= 1;
    }

}
