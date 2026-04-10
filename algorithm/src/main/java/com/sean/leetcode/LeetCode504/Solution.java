package com.sean.leetcode.LeetCode504;

/**
 * @Author xionghaiyang
 * @Date 2026-04-10 12:12
 * @Description https://leetcode.cn/problems/base-7
 * 504. 七进制数
 * 给定一个整数 num，将其转化为 7 进制，并以字符串形式输出。
 * -10^7 <= num <= 10^7
 */
public class Solution {

    public String convertToBase7(int num) {
        if (num == 0) {
            return "0";
        } else if (num < 0) {
            return "-" + convertToBase7(-num);
        }
        StringBuilder res = new StringBuilder();
        while (num != 0) {
            res.append(num % 7);
            num /= 7;
        }
        return res.reverse().toString();
    }

}
