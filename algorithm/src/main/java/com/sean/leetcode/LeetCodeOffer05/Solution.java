package com.sean.leetcode.LeetCodeOffer05;

/**
 * @Auther: xionghaiyang
 * @Date: 2022-09-27 20:24
 * @Description: https://leetcode.cn/problems/ti-huan-kong-ge-lcof/?plan=lcof&plan_progress=zq3t7ii
 * 剑指 Offer 05. 替换空格
 * 请实现一个函数，把字符串 s 中的每个空格替换成"%20"。
 */
public class Solution {

    public String replaceSpace(String s) {
        StringBuilder res = new StringBuilder();
        int n = s.length();
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (c == ' ') {
                res.append("%20");
            } else {
                res.append(c);
            }
        }
        return res.toString();
    }

}
