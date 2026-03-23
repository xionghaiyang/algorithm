package com.sean.leetcode.LeetCodeInterview1607;

/**
 * @Author xionghaiyang
 * @Date 2026-03-23 12:06
 * @Description https://leetcode.cn/problems/maximum-lcci
 * 面试题 16.07. 最大数值
 * 编写一个方法，找出两个数字a和b中最大的那一个。
 * 不得使用if-else或其他比较运算符。
 */
public class Solution {

    public int maximum(int a, int b) {
        int c = a - b;
        int sa = sign(a);
        int sb = sign(b);
        int sc = sign(c);
        //a、b符号相同结果为0，不同结果为1
        int difSab = sa ^ sb;
        //a、b符号相同结果为1，不同结果为0
        int sameSab = flip(difSab);
        //返回a的条件:
        // 1、a和b符号相同，且a -b >0
        // 2、a和b符号不同且a>0
        int returnA = sameSab * sc + difSab * sa;
        int returnB = flip(returnA);
        return a * returnA + b * returnB;
    }

    //1->0,0->1
    private int flip(int n) {
        return n ^ 1;
    }

    //非负数返回1，负数返回0
    private int sign(int n) {
        return flip((n >> 31) & 1);
    }

}
