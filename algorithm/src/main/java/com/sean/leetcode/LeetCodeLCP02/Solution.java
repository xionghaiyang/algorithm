package com.sean.leetcode.LeetCodeLCP02;

/**
 * @Auther: xionghaiyang
 * @Date: 2024-01-10 11:10
 * @Description: https://leetcode.cn/problems/deep-dark-fraction/description/
 * LCP 02. 分式化简
 * 有一个同学在学习分式。
 * 他需要将一个连分数化成最简分数，你能帮助他吗？
 * 连分数是形如上图的分式。
 * 在本题中，所有系数都是大于等于0的整数。
 * 输入的cont代表连分数的系数（cont[0]代表上图的a0，以此类推）。
 * 返回一个长度为2的数组[n, m]，使得连分数的值等于n / m，且n, m最大公约数为1。
 */
public class Solution {

    public int[] fraction(int[] cont) {
        return process(cont, 0);
    }

    private int[] process(int[] cont, int i) {
        if (i == cont.length - 1) {
            return new int[]{cont[cont.length - 1], 1};
        }
        int[] res = process(cont, i + 1);
        return new int[]{cont[i] * res[0] + res[1], res[0]};
    }

    public int[] fraction1(int[] cont) {
        int n = cont[cont.length - 1];
        int m = 1;
        for (int i = cont.length - 2; i >= 0; i--) {
            int tmp = n;
            n = cont[i] * n + m;
            m = tmp;
        }
        return new int[]{n, m};
    }

}
