package com.sean.leetcode.LeetCodeInterview1609;

/**
 * @Author xionghaiyang
 * @Date 2026-03-23 13:00
 * @Description https://leetcode.cn/problems/operations-lcci
 * 面试题 16.09. 运算
 * 请实现整数数字的乘法、减法和除法运算，运算结果均为整数数字，程序中只允许使用加法运算符和逻辑运算符，允许程序中出现正负常数，不允许使用位运算。
 * 你的实现应该支持如下操作：
 * Operations() 构造函数
 * minus(a, b) 减法，返回a - b
 * multiply(a, b) 乘法，返回a * b
 * divide(a, b) 除法，返回a / b
 * 你可以假设函数输入一定是有效的，例如不会出现除法分母为0的情况
 * 单个用例的函数调用次数不会超过1000次
 */
public class Operations {

    public Operations() {
    }

    //减法
    public int minus(int a, int b) {
        return add(a, negNum(b));
    }

    //乘法
    public int multiply(int a, int b) {
        int res = 0;
        while (b != 0) {
            if ((b & 1) != 0) {
                res = add(res, a);
            }
            a <<= 1;
            b >>>= 1;
        }
        return res;
    }

    //除法
    public int divide(int a, int b) {
        if (a == Integer.MIN_VALUE && b == Integer.MIN_VALUE) {
            return 1;
        } else if (b == Integer.MIN_VALUE) {
            return 0;
        } else if (a == Integer.MIN_VALUE) {
            if (b == negNum(1)) {
                return Integer.MAX_VALUE;
            } else {
                int c = div(add(a, 1), b);
                return add(c, divide(minus(a, multiply(c, b)), b));
            }
        } else {
            return div(a, b);
        }
    }

    private int div(int a, int b) {
        int x = isNeg(a) ? negNum(a) : a;
        int y = isNeg(b) ? negNum(b) : b;
        int res = 0;
        for (int i = 30; i >= 0; i = minus(i, 1)) {
            if ((x >> i) >= y) {
                res = add(res, 1 << i);
                x = minus(x, y << i);
            }
        }
        return isNeg(a) ^ isNeg(b) ? negNum(res) : res;
    }

    //是否是负数
    private boolean isNeg(int n) {
        return n < 0;
    }

    private int add(int a, int b) {
        return a + b;
    }

    //相反数
    private int negNum(int a) {
        int res = 0;
        int init = a > 0 ? 1 : -1;
        int delta = init;
        while (a != 0) {
            if (a > 0 ^ add(a, delta) > 0) {
                delta = init;
            }
            a = add(a, delta);
            res = add(res, delta);
            delta = add(delta, delta);
        }
        return res;
    }

}
