package com.sean.lintcode.LintCode2325;

import java.util.Scanner;

/**
 * @Auther: xionghaiyang
 * @Date: 2022-09-28 08:54
 * @Description: https://www.lintcode.com/problem/2325/?showListFe=true&page=1&pageSize=50
 * 2325 · 逆序输出字符串（Java 版）
 * 描述
 * 给出一个字符串 s，要求使用 System.out.print 语句逆序输出该字符串。
 */
public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.next();
        System.out.print(new StringBuilder(s).reverse());
        sc.close();
    }

}
