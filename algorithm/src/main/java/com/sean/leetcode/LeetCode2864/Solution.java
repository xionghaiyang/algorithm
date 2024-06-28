package com.sean.leetcode.LeetCode2864;

/**
 * @Auther: xionghaiyang
 * @Date: 2024-03-13 13:12
 * @Description: https://leetcode.cn/problems/maximum-odd-binary-number/
 * 2864. 最大二进制奇数
 * 给你一个 二进制 字符串 s ，其中至少包含一个 '1' 。
 * 你必须按某种方式 重新排列 字符串中的位，使得到的二进制数字是可以由该组合生成的 最大二进制奇数 。
 * 以字符串形式，表示并返回可以由给定组合生成的最大二进制奇数。
 * 注意 返回的结果字符串 可以 含前导零。
 */
public class Solution {

    public String maximumOddBinaryNumber(String s) {
        int n = s.length();
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            cnt += s.charAt(i) - '0';
        }
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < cnt - 1; i++) {
            res.append('1');
        }
        for (int i = 0; i < n - cnt; i++) {
            res.append('0');
        }
        res.append('1');
        return res.toString();
    }

}
