package com.sean.lintcode.LintCode730;

/**
 * @Auther: xionghaiyang
 * @Date: 2022-08-31 08:42
 * @Description: https://www.lintcode.com/problem/730/?showListFe=true&page=1&pageSize=50
 * 730 · 所有子集的和
 * 描述
 * 给一整数 n, 我们需要求前n个自然数形成的集合的所有可能子集中所有元素的和。
 */
public class Solution {

    public int subSum(int n) {
        return n * (n + 1) / 2 * (int) Math.pow(2, n - 1);
    }

}
