package com.sean.leetcode.LeetCode1694;

/**
 * @Auther: xionghaiyang
 * @Date: 2022-10-01 23:07
 * @Description: https://leetcode.cn/problems/reformat-phone-number/
 * 1694. 重新格式化电话号码
 * 给你一个字符串形式的电话号码 number 。number 由数字、空格 ' '、和破折号 '-' 组成。
 * 请你按下述方式重新格式化电话号码。
 * 首先，删除 所有的空格和破折号。
 * 其次，将数组从左到右 每 3 个一组 分块，直到 剩下 4 个或更少数字。剩下的数字将按下述规定再分块：
 * 2 个数字：单个含 2 个数字的块。
 * 3 个数字：单个含 3 个数字的块。
 * 4 个数字：两个分别含 2 个数字的块。
 * 最后用破折号将这些块连接起来。注意，重新格式化过程中 不应该 生成仅含 1 个数字的块，并且 最多 生成两个含 2 个数字的块。
 * 返回格式化后的电话号码。
 */
public class Solution {

    public String reformatNumber(String number) {
        StringBuilder digits = new StringBuilder();
        int n = number.length();
        for (int i = 0; i < n; i++) {
            char ch = number.charAt(i);
            if (Character.isDigit(ch)) {
                digits.append(ch);
            }
        }
        int m = digits.length();
        StringBuilder res = new StringBuilder();
        int index = 0;
        while (m > 0) {
            if (m > 4) {
                res.append(digits.substring(index, index + 3)).append("-");
                index += 3;
                m -= 3;
            } else {
                if (m == 4) {
                    res.append(digits.substring(index, index + 2)).append("-").append(digits.substring(index + 2, index + 4));
                } else {
                    res.append(digits.substring(index, index + m));
                }
                break;
            }
        }
        return res.toString();
    }

}
