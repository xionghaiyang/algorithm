package com.sean.leetcode.LeetCodeInterview0105;

/**
 * @Auther: xionghaiyang
 * @Date: 2022-09-23 12:50
 * @Description: https://leetcode.cn/problems/one-away-lcci
 * 面试题 01.05. 一次编辑
 * 字符串有三种编辑操作:插入一个英文字符、删除一个英文字符或者替换一个英文字符。
 * 给定两个字符串，编写一个函数判定它们是否只需要一次(或者零次)编辑。
 */
public class Solution {

    public boolean oneEditAway(String first, String second) {
        int m = first.length(), n = second.length();
        if (n - m == 1) {
            return process(first, second);
        } else if (m - n == 1) {
            return process(second, first);
        } else if (m == n) {
            boolean flag = false;
            for (int i = 0; i < m; i++) {
                if (first.charAt(i) != second.charAt(i)) {
                    if (!flag) {
                        flag = true;
                    } else {
                        return false;
                    }
                }
            }
            return true;
        } else {
            return false;
        }
    }

    private boolean process(String shorter, String longer) {
        int m = shorter.length(), n = longer.length();
        int i = 0, j = 0;
        while (i < m && j < n) {
            if (shorter.charAt(i) == longer.charAt(j)) {
                i++;
            }
            j++;
            if (j - i > 1) {
                return false;
            }
        }
        return true;
    }

}
