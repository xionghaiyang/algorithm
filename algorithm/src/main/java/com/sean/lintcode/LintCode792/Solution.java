package com.sean.lintcode.LintCode792;

/**
 * @Auther: xionghaiyang
 * @Date: 2022-09-25 20:57
 * @Description: https://www.lintcode.com/problem/792/description?showListFe=true&page=1&pageSize=50
 * 792 · 第K个质数
 * 给出质数n，输出它是第几个质数。
 */
public class Solution {

    public int kthPrime(int n) {
        boolean[] prime = new boolean[n + 1];
        for (int i = 2; i < n; i++) {
            if (!prime[i]) {
                for (int j = 2 * i; j < n; j += i) {
                    prime[j] = true;
                }
            }
        }
        int res = 1;
        for (int i = 2; i < n; i++) {
            if (!prime[i]) {
                res++;
            }
        }
        return res;
    }

}
