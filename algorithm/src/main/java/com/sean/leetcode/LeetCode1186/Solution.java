package com.sean.leetcode.LeetCode1186;

/**
 * @Author xionghaiyang
 * @Date 2024-07-21 06:59
 * @Description https://leetcode.cn/problems/maximum-subarray-sum-with-one-deletion/
 * 1186. 删除一次得到子数组最大和
 * 给你一个整数数组，返回它的某个 非空 子数组（连续元素）在执行一次可选的删除操作后，所能得到的最大元素总和。
 * 换句话说，你可以从原数组中选出一个子数组，并可以决定要不要从中删除一个元素（只能删一次哦），
 * （删除后）子数组中至少应当有一个元素，然后该子数组（剩下）的元素总和是所有子数组之中最大的。
 * 注意，删除一个元素后，子数组 不能为空。
 */
public class Solution {

    public int maximumSum(int[] arr) {
        int n = arr.length;
        int res = arr[0];
        //dp[i][k]表示删除前以arr[i]结尾，删除k次的非空子数组的最大值
        int[][] dp = new int[n][2];
        dp[0][0] = arr[0];
        dp[0][1] = 0;
        for (int i = 1; i < n; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], 0) + arr[i];
            dp[i][1] = Math.max(dp[i - 1][0], dp[i - 1][1] + arr[i]);
            res = Math.max(res, Math.max(dp[i][0], dp[i][1]));
        }
        return res;
    }

    public int maximumSum1(int[] arr) {
        int n = arr.length;
        int res = arr[0];
        int dp0 = arr[0];
        int dp1 = 0;
        for (int i = 1; i < n; i++) {
            dp1 = Math.max(dp0, dp1 + arr[i]);
            dp0 = Math.max(dp0, 0) + arr[i];
            res = Math.max(res, Math.max(dp0, dp1));
        }
        return res;
    }

}
