package com.sean.leetcode.LeetCode2106;

/**
 * @Auther: xionghaiyang
 * @Date: 2023-05-04 08:17
 * @Description: https://leetcode.cn/problems/maximum-fruits-harvested-after-at-most-k-steps/
 * 2106. 摘水果
 * 在一个无限的 x 坐标轴上，有许多水果分布在其中某些位置。
 * 给你一个二维整数数组 fruits ，其中 fruits[i] = [positioni, amounti] 表示共有 amounti 个水果放置在 positioni 上。
 * fruits 已经按 positioni 升序排列 ，每个 positioni 互不相同 。
 * 另给你两个整数 startPos 和 k 。最初，你位于 startPos 。
 * 从任何位置，你可以选择 向左或者向右 走。
 * 在 x 轴上每移动 一个单位 ，就记作 一步 。
 * 你总共可以走 最多 k 步。你每达到一个位置，都会摘掉全部的水果，水果也将从该位置消失（不会再生）。
 * 返回你可以摘到水果的 最大总数 。
 */
public class Solution {

    public int maxTotalFruits1(int[][] fruits, int startPos, int k) {
        int n = fruits.length;
        int[] indices = new int[n];
        int[] sum = new int[n + 1];
        sum[0] = 0;
        for (int i = 0; i < n; i++) {
            indices[i] = fruits[i][0];
            sum[i + 1] = sum[i] + fruits[i][1];
        }
        int res = 0;
        for (int x = 0; x <= k / 2; x++) {
            //向左走x步，再向右走k-x步
            int left = startPos - x;
            int right = left + (k - x);
            int start = lowerBound(indices, 0, n - 1, left);
            int end = upperBound(indices, 0, n - 1, right);
            res = Math.max(res, sum[end] - sum[start]);
            //向右走x步，再向左走k-x步
            right = startPos + x;
            left = right - (k - x);
            start = lowerBound(indices, 0, n - 1, left);
            end = upperBound(indices, 0, n - 1, right);
            res = Math.max(res, sum[end] - sum[start]);
        }
        return res;
    }

    private int lowerBound(int[] arr, int left, int right, int val) {
        int res = right + 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] >= val) {
                res = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return res;
    }

    private int upperBound(int[] arr, int left, int right, int val) {
        int res = right + 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] > val) {
                res = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return res;
    }

    public int maxTotalFruits(int[][] fruits, int startPos, int k) {
        int left = 0;
        int right = 0;
        int n = fruits.length;
        int sum = 0;
        int res = 0;
        //每次固定住窗口右边界
        while (right < n) {
            sum += fruits[right][1];
            //移动左边界
            while (left <= right && step(fruits, startPos, left, right) > k) {
                sum -= fruits[left][1];
                left++;
            }
            res = Math.max(res, sum);
            right++;
        }
        return res;
    }

    private int step(int[][] fruits, int startPos, int left, int right) {
        return Math.min(Math.abs(startPos - fruits[right][0]), Math.abs(startPos - fruits[left][0])) + fruits[right][0] - fruits[left][0];
    }

}
