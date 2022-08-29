package com.sean.lintcode.LintCode517;

/**
 * @Auther: xionghaiyang
 * @Date: 2022-08-29 08:40
 * @Description: https://www.lintcode.com/problem/517/?showListFe=true&page=1&pageSize=50
 * 517 · 丑数
 * 描述
 * 写一个程序来检测一个整数是不是丑数。
 * 丑数的定义是，只包含质因子 2, 3, 5 的正整数。比如 6, 8 就是丑数，但是 14 不是丑数因为他包含了质因子 7。
 */
public class Solution {

    public boolean isUgly(int num) {
        if (num <= 0) {
            return false;
        }
        while (num % 2 == 0) {
            num /= 2;
        }
        while (num % 3 == 0) {
            num /= 3;
        }
        while (num % 5 == 0) {
            num /= 5;
        }
        return num == 1;
    }

}
