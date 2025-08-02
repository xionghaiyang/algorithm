package com.sean.leetcode.LeetCode2106;

/**
 * @Auther: xionghaiyang
 * @Date: 2023-05-04 08:17
 * @Description: https://leetcode.cn/problems/maximum-fruits-harvested-after-at-most-k-steps
 * 2106. 摘水果
 * 在一个无限的 x 坐标轴上，有许多水果分布在其中某些位置。
 * 给你一个二维整数数组 fruits ，其中 fruits[i] = [positioni, amounti] 表示共有 amounti 个水果放置在 positioni 上。
 * fruits 已经按 positioni 升序排列 ，每个 positioni 互不相同 。
 * 另给你两个整数 startPos 和 k 。
 * 最初，你位于 startPos 。
 * 从任何位置，你可以选择 向左或者向右 走。
 * 在 x 轴上每移动 一个单位 ，就记作 一步 。
 * 你总共可以走 最多 k 步。
 * 你每达到一个位置，都会摘掉全部的水果，水果也将从该位置消失（不会再生）。
 * 返回你可以摘到水果的 最大总数 。
 * 1 <= fruits.length <= 10^5
 * fruits[i].length == 2
 * 0 <= startPos, positioni <= 2 * 10^5
 * 对于任意 i > 0 ，positioni-1 < positioni 均成立（下标从 0 开始计数）
 * 1 <= amounti <= 10^4
 * 0 <= k <= 2 * 10^5
 */
public class Solution {

    public int maxTotalFruits(int[][] fruits, int startPos, int k) {
        int n = fruits.length;
        int res = 0;
        for (int left = binarySearch(fruits, startPos - k), right = left, sum = 0; right < n && fruits[right][0] <= startPos + k; right++) {
            sum += fruits[right][1];
            while (left <= right && getStep(fruits, startPos, left, right) > k) {
                sum -= fruits[left++][1];
            }
            res = Math.max(res, sum);
        }
        return res;
    }

    private int getStep(int[][] fruits, int startPos, int left, int right) {
        return fruits[right][0] - fruits[left][0] + Math.min(Math.abs(startPos - fruits[right][0]), Math.abs(startPos - fruits[left][0]));
    }

    private int binarySearch(int[][] fruits, int k) {
        int left = 0, right = fruits.length - 1, res = 0;
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            if (fruits[mid][0] >= mid) {
                res = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return res;
    }

}
