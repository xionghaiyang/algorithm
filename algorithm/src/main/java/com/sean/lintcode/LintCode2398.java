package com.sean.lintcode;

import java.util.Scanner;

/**
 * @Auther: xionghaiyang
 * @Date: 2022-07-29 10:57
 */
public class LintCode2398 {

    /**
     * https://www.lintcode.com/problem/2398/?showListFe=true&page=1&pageSize=50
     * 请从标准输入流（控制台）中获取一个两位正整数 n，
     * 通过 System.out.println 语句输出该正整数的逆序到标准输出流（控制台）。
     */

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int res = 0;
        while (n != 0) {
            res = res * 10 + (n % 10);
            n /= 10;
        }
        System.out.println(res);
    }

}
