package com.sean.lintcode.LintCode2166;

/**
 * @Auther: xionghaiyang
 * @Date: 2022-09-08 08:44
 * @Description: https://www.lintcode.com/problem/2166/?showListFe=true&page=1&problemTypeId=11&pageSize=50
 * 2166 · 简单的加减乘除运算
 * 本题需计算 a 与 b 的加减乘除，我们推荐使用 +，-，*，/ 来实现，
 * 本题提供了 Solution 类，Solution 类中有四个方法，addition（加法），subtraction（减法），multiplication（乘法），division （除法）。
 */
public class Solution {

    public int addition(int a, int b) {
        return a + b;
    }

    public int subtraction(int a, int b) {
        return a - b;
    }

    public int multiplication(int a, int b) {
        return a * b;
    }

    public float division(int a, int b) {
        return (float) a / b;
    }

}
