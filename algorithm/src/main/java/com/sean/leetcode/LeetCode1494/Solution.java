package com.sean.leetcode.LeetCode1494;

import java.util.Arrays;

/**
 * @Auther: xionghaiyang
 * @Date: 2023-06-16 09:42
 * @Description: https://leetcode.cn/problems/parallel-courses-ii/
 * 1494. 并行课程 II
 * 给你一个整数 n 表示某所大学里课程的数目，编号为 1 到 n ，
 * 数组 relations 中， relations[i] = [xi, yi]  表示一个先修课的关系，也就是课程 xi 必须在课程 yi 之前上。同时你还有一个整数 k 。
 * 在一个学期中，你 最多 可以同时上 k 门课，前提是这些课的先修课在之前的学期里已经上过了。
 * 请你返回上完所有课最少需要多少个学期。题目保证一定存在一种上完所有课的方式。
 */
public class Solution {

    public int minNumberOfSemesters(int n, int[][] relations, int k) {
        //dp[i]:完成学习的课程集合为i所需要的最少学期数
        int[] dp = new int[1 << n];
        Arrays.fill(dp, Integer.MAX_VALUE);
        //need[i]:完成学习课程集合为i的先修课程集合
        int[] need = new int[1 << n];
        for (int[] edge : relations) {
            need[(1 << (edge[1] - 1))] |= (1 << (edge[0] - 1));
        }
        dp[0] = 0;
        for (int i = 1; i < (1 << n); i++) {
            //x&(x-1)是去掉最右边的1
            //x&(-x)是最右边的1
            need[i] = need[i & (i - 1)] | need[i & (-i)];
            if ((need[i] | i) != i) {//i中有任意一门课程的前置课程没有完成学习
                continue;
            }
            int valid = i ^ need[i];//当前学期可以进行学习的课程集合
            if (Integer.bitCount(valid) <= k) {//如果个数小于k,则可以全部学习，不再枚举子集
                dp[i] = Math.min(dp[i], dp[i ^ valid] + 1);
            } else {//否则枚举当前学期需要进行学习的课程集合
                for (int sub = valid; sub > 0; sub = (sub - 1) & valid) {
                    if (Integer.bitCount(sub) <= k) {
                        dp[i] = Math.min(dp[i], dp[i ^ sub] + 1);
                    }
                }
            }
        }
        return dp[(1 << n) - 1];
    }

}
