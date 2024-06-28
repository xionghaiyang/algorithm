package com.sean.leetcode.LeetCode1528;

/**
 * @Auther: xionghaiyang
 * @Date: 2024-01-01 14:03
 * @Description: https://leetcode.cn/problems/shuffle-string/description/
 * 1528. 重新排列字符串
 * 给你一个字符串 s 和一个 长度相同 的整数数组 indices 。
 * 请你重新排列字符串 s ，其中第 i 个字符需要移动到 indices[i] 指示的位置。
 * 返回重新排列后的字符串。
 */
public class Solution {

    public String restoreString(String s, int[] indices) {
        int n = s.length();
        char[] res = new char[n];
        for (int i = 0; i < n; i++) {
            res[indices[i]] = s.charAt(i);
        }
        return new String(res);
    }

}
