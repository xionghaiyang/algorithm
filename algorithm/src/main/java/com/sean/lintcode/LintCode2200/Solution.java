package com.sean.lintcode.LintCode2200;

/**
 * @Auther: xionghaiyang
 * @Date: 2022-08-20 20:16
 * @Description: https://www.lintcode.com/problem/2200/?showListFe=true&page=1&pageSize=50
 * 2200 · 计算从 1949 年到 n 年中所有的闰年
 * 描述
 * 请编写 Java 语句，计算从 1949 年到 n 年（包括 n 年）中所有的闰年。
 * 本题提供了 Solution 类 ，Solution 类中有一个 leapYear 方法，
 * 该方法传递了一个 int 类型的变量 year，该方法将会打印所有符合要求的年数。
 */
public class Solution {

    public void leapYear(int year) {
        for (int i = 1949; i <= year; i++) {
            if ((i % 100 != 0 && i % 4 == 0) || (i % 400 == 0)) {
                System.out.println(i);
            }
        }
    }

}
