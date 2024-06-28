package com.sean.lintcode.LintCode2835;

import java.util.Scanner;

/**
 * @Auther: xionghaiyang
 * @Date: 2022-08-26 08:47
 * @Description: https://www.lintcode.com/problem/2835/?showListFe=true&page=1&pageSize=50
 * 2835 · 求两个数的最大值与最小值
 * 描述
 * 你的代码需要从标准输入流（控制台）读入两个整数，判断这两个数的最大值和最小值分别为多少，并将结果打印到标准输出流（控制台）中。
 */
public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int a = sc.nextInt();
        int b = sc.nextInt();
        System.out.println("The maximum value of the two numbers is: " + (a > b ? a : b));
        System.out.println("The minimum value of the two numbers is: " + (a > b ? b : a));
    }

}
