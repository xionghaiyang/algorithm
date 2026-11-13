package com.sean.leetcode.LeetCode1478;

import java.util.Arrays;

/**
 * @Author: xionghaiyang
 * @Date: 2026-11-13 12:53
 * @Description: https://leetcode.cn/problems/allocate-mailboxes
 * 1478. 安排邮筒
 * 给你一个房屋数组houses 和一个整数 k ，其中 houses[i] 是第 i 栋房子在一条街上的位置，现需要在这条街上安排 k 个邮筒。
 * 请你返回每栋房子与离它最近的邮筒之间的距离的 最小 总和。
 * 答案保证在 32 位有符号整数范围以内。
 * n == houses.length
 * 1 <= n <= 100
 * 1 <= houses[i] <= 10^4
 * 1 <= k <= n
 * 数组 houses 中的整数互不相同。
 */
public class Solution {

    public int minDistance(int[] houses, int k) {
        Arrays.sort(houses);
        int n = houses.length;
        //cost[i][j]:在houses[i..j]之间放一个邮筒的最小距离和
        int[][] cost = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                int mid = (i + j) >> 1;
                int medianPos = houses[mid];
                int sum = 0;
                for (int m = i; m <= j; m++) {
                    sum += Math.abs(houses[m] - medianPos);
                }
                cost[i][j] = sum;
            }
        }
        //dp[i][j]:houses[0..i]使用j个邮筒的最小距离和
        int[][] dp = new int[n][k + 1];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE / 2);
        }
        for (int i = 0; i < n; i++) {
            dp[i][1] = cost[0][i];
        }
        for (int j = 2; j <= k; j++) {
            for (int i = j - 1; i < n; i++) {
                //第j个邮筒负责[t+1,i]
                for (int t = j - 2; t < i; t++) {
                    dp[i][j] = Math.min(dp[i][j], dp[t][j - 1] + cost[t + 1][i]);
                }
            }
        }
        return dp[n - 1][k];
    }

    public int minDistance1(int[] houses, int k) {
        Arrays.sort(houses);
        int n = houses.length;
        //cost[i][j]:在houses[i..j]之间放一个邮筒的最小距离和
        int[][] cost = new int[n][n];
        for (int i = n - 2; i >= 0; i--) {
            for (int j = i + 1; j < n; j++) {
                cost[i][j] = cost[i + 1][j - 1] + houses[j] - houses[i];
            }
        }
        //dp[i][j]:houses[0..i]使用j个邮筒的最小距离和
        int[][] dp = new int[n][k + 1];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE / 2);
        }
        for (int i = 0; i < n; i++) {
            dp[i][1] = cost[0][i];
        }
        for (int j = 2; j <= k; j++) {
            for (int i = j - 1; i < n; i++) {
                //第j个邮筒负责[t+1,i]
                for (int t = j - 2; t < i; t++) {
                    dp[i][j] = Math.min(dp[i][j], dp[t][j - 1] + cost[t + 1][i]);
                }
            }
        }
        return dp[n - 1][k];
    }

}
