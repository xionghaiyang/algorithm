package com.sean.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 三角形最小路径和
 * https://leetcode-cn.com/problems/triangle/
 */
public class LeetCode120 {

    public static int minimumTotal(List<List<Integer>> triangle) {
        int n = triangle.size();
        int[][] dp = new int[n][n];
        dp[0][0] = triangle.get(0).get(0);
        for (int i = 1; i < n; i++) {
            dp[i][0] = dp[i - 1][0] + triangle.get(i).get(0);
            for (int j = 1; j < i; j++) {
                dp[i][j] = Math.min(dp[i - 1][j - 1], dp[i - 1][j]) + triangle.get(i).get(j);
            }
            dp[i][i] = dp[i - 1][i - 1] + triangle.get(i).get(i);
        }
        int res = dp[n - 1][0];
        for (int i = 1; i < n; i++) {
            res = Math.min(res, dp[n - 1][i]);
        }
        return res;
    }

    private static List<List<Integer>> arrToList(int[][] arr) {
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            List<Integer> list = new ArrayList<>();
            for (int j = 0; j < arr[i].length; j++) {
                list.add(arr[i][j]);
            }
            res.add(list);
        }
        return res;
    }


    public static void main(String[] args) {
        System.out.println(minimumTotal(arrToList(new int[][]{{2}, {3, 4}, {6, 5, 7}, {4, 1, 8, 3}})));
        System.out.println(minimumTotal(arrToList(new int[][]{{-10}})));
    }

}
