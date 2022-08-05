package com.sean.lintcode.LintCode2;

/**
 * @Auther: xionghaiyang
 * @Date: 2022-08-05 11:50
 * @Description: https://www.lintcode.com/problem/2/?showListFe=true&page=1&pageSize=50
 * 2 尾部的零
 * 给定一个整数 n，计算出n!中尾部零的个数。
 */
public class Solution {

    public long trailingZeros(long n) {
        long res = 0;
        while (n != 0) {
            n /= 5;
            res += n;
        }
        return res;
    }

}
