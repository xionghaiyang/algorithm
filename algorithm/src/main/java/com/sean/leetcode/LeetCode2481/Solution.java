package com.sean.leetcode.LeetCode2481;

/**
 * @Auther: xionghaiyang
 * @Date: 2023-06-18 10:14
 * @Description: https://leetcode.cn/problems/minimum-cuts-to-divide-a-circle/
 * 2481. 分割圆的最少切割次数
 * 圆内一个 有效切割 ，符合以下二者之一：
 * 该切割是两个端点在圆上的线段，且该线段经过圆心。
 * 该切割是一端在圆心另一端在圆上的线段。
 * 一些有效和无效的切割如下图所示。
 * 给你一个整数 n ，请你返回将圆切割成相等的 n 等分的 最少 切割次数。
 */
public class Solution {

    public int numberOfCuts(int n) {
        if (n == 1) {
            return 0;
        }
        if (n % 2 == 0) {
            return n / 2;
        }
        return n;
    }

}
