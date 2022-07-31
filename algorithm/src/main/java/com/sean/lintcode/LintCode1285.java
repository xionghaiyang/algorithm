package com.sean.lintcode;

/**
 * 给定一个整数（32位有符号整数），写一个方法判断这个数字是否为4的乘方。
 */
public class LintCode1285 {

    public static boolean isPowerOfFour(int num) {
        //大于0、是2的倍数、数字1在奇数位上
        return num > 0 && (num & (num - 1)) == 0 && (num & 0x55555555) > 0;
    }

}
