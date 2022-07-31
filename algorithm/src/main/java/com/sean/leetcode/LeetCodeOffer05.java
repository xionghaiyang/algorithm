package com.sean.leetcode;

/**
 * https://leetcode-cn.com/problems/ti-huan-kong-ge-lcof/
 * 剑指 Offer 05. 替换空格
 */
public class LeetCodeOffer05 {

    public String replaceSpace(String s) {
        StringBuilder res = new StringBuilder();
        for (char c : s.toCharArray()) {
            if (c == ' ') {
                res.append("%20");
            } else {
                res.append(c);
            }
        }
        return res.toString();
    }

}
