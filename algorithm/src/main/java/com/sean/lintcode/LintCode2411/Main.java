package com.sean.lintcode.LintCode2411;

import java.util.Scanner;

/**
 * @Auther: xionghaiyang
 * @Date: 2022-08-24 11:32
 * @Description: https://www.lintcode.com/problem/2411/?showListFe=true&page=1&pageSize=50
 * 2411 · 打印每月含有的天数
 * 你的代码需要从标准输入流（控制台）中读入一个 String 的数组，
 * 保证数组的第一个元素是年份，第二个元素是月份，计算出结果并打印到标准输出流（控制台）中。
 */
public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int year = sc.nextInt();
        int month = sc.nextInt();
        switch (month) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                System.out.println("31 days");
                break;
            case 4:
            case 6:
            case 9:
            case 11:
                System.out.println("30 days");
                break;
            case 2:
                if ((year % 4 == 0 && year % 100 == 0) || (year % 400 == 0)) {
                    System.out.println("29 days");
                } else {
                    System.out.println("28 days");
                }
                break;
        }
    }

}
