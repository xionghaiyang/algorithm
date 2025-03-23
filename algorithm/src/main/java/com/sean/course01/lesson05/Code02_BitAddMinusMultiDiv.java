package com.sean.course01.lesson05;

/**
 * @Author xionghaiyang
 * @Date 2025-03-23 20:48
 * @Description https://leetcode.cn/problems/divide-two-integers
 * 使用位运算实现加减乘除
 */
public class Code02_BitAddMinusMultiDiv {

    //a+b
    public int add(int a, int b) {
        int res = a;
        while (b != 0) {
            //异或就是无进位相加
            res = a ^ b;
            //进位
            b = (a & b) << 1;
            a = res;
        }
        return res;
    }

    //-x = ~x+1
    public int negNum(int n) {
        return add(~n, 1);
    }

    //a-b
    public int minus(int a, int b) {
        return add(a, negNum(b));
    }

    //a*b
    public int multi(int a, int b) {
        int res = 0;
        while (b != 0) {
            //末位为1
            if ((b & 1) != 0) {
                res = add(res, a);
            }
            //带符号左移
            a <<= 1;
            //不带符号右移
            b >>>= 1;
        }
        return res;
    }

    //是否是负数
    public boolean isNeg(int n) {
        return n < 0;
    }

    public int div(int a, int b) {
        int x = isNeg(a) ? negNum(a) : a;
        int y = isNeg(b) ? negNum(b) : b;
        int res = 0;
        for (int i = 30; i >= 0; i = minus(i, 1)) {
            if ((x >> i) >= y) {
                res |= (1 << i);
                x = minus(x, y << i);
            }
        }
        return isNeg(a) ^ isNeg(b) ? negNum(res) : res;
    }

    public int divide(int a, int b) {
        if (a == Integer.MIN_VALUE && b == Integer.MIN_VALUE) {
            return 1;
        } else if (b == Integer.MIN_VALUE) {
            return 0;
        } else if (a == Integer.MIN_VALUE) {
            if (b == negNum(1)) {
                return Integer.MAX_VALUE;
            } else {
                //c = (a+1)/b
                int c = div(add(a, 1), b);
                //c + (a-c*b)/b
                return add(c, divide(minus(a, multi(c, b)), b));
            }
        } else {
            return div(a, b);
        }
    }

}
