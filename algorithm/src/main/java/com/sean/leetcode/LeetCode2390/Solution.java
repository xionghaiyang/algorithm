package com.sean.leetcode.LeetCode2390;

/**
 * @Auther: xionghaiyang
 * @Date: 2023-06-07 21:48
 * @Description: https://leetcode.cn/problems/removing-stars-from-a-string/?envType=study-plan-v2&envId=leetcode-75
 * 2390. 从字符串中移除星号
 * 给你一个包含若干星号 * 的字符串 s 。
 * 在一步操作中，你可以：
 * 选中 s 中的一个星号。
 * 移除星号 左侧 最近的那个 非星号 字符，并移除该星号自身。
 * 返回移除 所有 星号之后的字符串。
 * 注意：
 * 生成的输入保证总是可以执行题面中描述的操作。
 * 可以证明结果字符串是唯一的。
 */
public class Solution {

    public String removeStars(String s) {
        if (s == null || s.length() == 0) {
            return s;
        }
        int n = s.length();
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (c == '*') {
                if (res.length() > 0) {
                    res.deleteCharAt(res.length() - 1);
                }
            } else {
                res.append(c);
            }
        }
        return res.toString();
    }

}
