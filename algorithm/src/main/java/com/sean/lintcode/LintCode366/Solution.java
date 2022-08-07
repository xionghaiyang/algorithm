package com.sean.lintcode.LintCode366;

/**
 * @Author xionghaiyang
 * @Date 2022-08-07 13:10
 * @Description https://www.lintcode.com/problem/366/?showListFe=true&page=1&pageSize=50
 * 366 · 斐波纳契数列
 * 描述
 * 查找斐波纳契数列中第 N 个数。（N 从 0 开始）
 * 所谓的斐波纳契数列是指：
 * 前2个数是 0 和 1 。
 * 第 i 个数是第 i-1 个数和第i-2 个数的和。
 * 斐波纳契数列的前10个数字是：
 * 0, 1, 1, 2, 3, 5, 8, 13, 21, 34 ...
 */
public class Solution {

    public int fibonacci(int n) {
        if (n == 1) {
            return 0;
        }
        if (n == 2) {
            return 1;
        }
        int a = 0;
        int b = 1;
        int c = a + b;
        for (int i = 3; i <= n; i++) {
            c = a + b;
            a = b;
            b = c;
        }
        return c;
    }

}
